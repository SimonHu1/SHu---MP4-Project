import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.NumberFormat;
import javax.swing.text.NumberFormatter;
import javax.swing.border.LineBorder;
public class ShopItemLabel extends JLabel
{
    private JButton buyButton = new JButton("BUY");
    private JTextField itemName = new JTextField();
    private JTextField costField = new JTextField();
    private JFormattedTextField description = new JFormattedTextField();
    private JLabel imageLabel = new JLabel();
    private Gameplay game;
    private int itemNumber;
    private Inventory playerInventory;
    private Shop shop;
    private Upgrade upgrade;
    public ShopItemLabel(Shop shop, Upgrade upgrade, ImageIcon itemImage)
    {
        this.shop = shop;
        playerInventory=shop.getPlayerInventory();
        this.upgrade=upgrade;
        setLayout(null);
        NumberFormatter formatter = new NumberFormatter(NumberFormat.getIntegerInstance());
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(1);
        this.description.setColumns(10);
        imageLabel.setIcon(itemImage);
        itemName.setText(upgrade.getUpgradeName());
        itemNumber = upgrade.getUpgradeID();
        System.out.println(getCost());
        costField.setText("Cost: " + getCost());
        this.description.setText(updatedItemDescription(upgrade));
        this.description.setFont(new Font("Monospaced", Font.BOLD, 17));
        itemName.setFont(new Font("Monospaced", Font.BOLD, 20));
        costField.setFont(new Font("Monospaced", Font.BOLD, 20));
        buyButton.setFont(new Font("Monospaced", Font.BOLD, 52));
        costField.setFocusable(false);
        buyButton.setBackground(Color.GREEN);
        itemName.setEditable(false);
        costField.setEditable(false);
        this.description.setEditable(false);
        imageLabel.setBorder(new LineBorder(Color.BLACK));
        imageLabel.setBounds(0, 0, 180, 180);
        buyButton.setBounds(880, 0, 180, 180);
        itemName.setBounds(180, 0, 700, 60);
        costField.setBounds(180,120,700,60);
        this.description.setBounds(180,60,700,60);
        add(buyButton);
        add(imageLabel);
        add(itemName);
        add(costField);
        add(this.description);
        buyButton.addActionListener(this::buy);
    }
    public void updateLabel(Upgrade upgrade, ImageIcon itemImage)
    {
        this.upgrade = upgrade;
        imageLabel.setIcon(itemImage);
        itemName.setText(upgrade.getUpgradeName());
        itemNumber = upgrade.getUpgradeID();
        costField.setText("Cost: " + getCost());
        this.description.setText(updatedItemDescription(upgrade));
    }
    public int getCost()
    {
//        if(itemNumber==0)
//        {
//            int[] proteggtionCosts = new int[]{25,50,100,175,500,1000};
//            return proteggtionCosts[playerInventory.getUpgrades()[itemNumber]];
//        }
//        if(itemNumber==1)
//        {
//            int[] seedGaloreCosts = new int[]{25,50,100,175,500,1000};
//            return seedGaloreCosts[playerInventory.getUpgrades()[itemNumber]];
//        }
//        if(itemNumber==2)
//        {
//            int[] archaicCallCosts = new int[]{15,30,50,85,200,1000};
//            return archaicCallCosts[playerInventory.getUpgrades()[itemNumber]];
//        }
//        if(itemNumber==3)
//        {
//            int[] artOfTheCatCosts = new int[]{10,25,45,80,130,200,1000};
//            return artOfTheCatCosts[playerInventory.getUpgrades()[itemNumber]];
//        }
//        int[] featheringHeightsCosts = new int[]{5,8,15,25,40,60,90,125,200,1000};
//        return featheringHeightsCosts[playerInventory.getUpgrades()[itemNumber]];
        return upgrade.getUpgradePrice();
    }
    public void buy(ActionEvent event)
    {
        if(getCost()>999) return;
        if(playerInventory.getSeed()>=getCost())
        {
            playerInventory.setSeed(playerInventory.getSeed()-getCost());
            shop.incrementUpgrade(upgrade.getUpgradeID(),upgrade.getUpgradeRarity());
            shop.updateSeedCount();
            if(getCost()<999)
            {
                costField.setText("Cost: " + getCost());
            }
            else
            {
                costField.setText("Maxed");
                buyButton.setText("MAXED");
            }
        }
        this.setVisible(false);
    }
    public String updatedItemDescription(Upgrade up)
    {
        String temp = up.getDescription();
        if(temp.contains("~"))
        {
            temp = temp.replace("~",checkIntOrDouble(up.getBuff()[0][up.getUpgradeLevel()]));
        }
        if(temp.contains("'"))
        {
            temp =  temp.replace("'",checkIntOrDouble(up.getBuff()[1][up.getUpgradeLevel()]));
        }
        if(temp.contains("_"))
        {
            temp = temp.replace("_",checkIntOrDouble(up.getBuff()[2][up.getUpgradeLevel()]));
        }
        return temp;
    }
    public String checkIntOrDouble(double val)
    {
        if(val*1000==((int)val)*1000) return (int)val+"";
        else return val+"";
    }
    public Upgrade getUpgrade()
    {
        return upgrade;
    }
}
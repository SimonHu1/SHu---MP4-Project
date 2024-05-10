package Projectiles;

import Projectiles.Obstacle;

import java.awt.image.BufferedImage;
public class Tractor extends Obstacle
{
    public Tractor(int width, int height, BufferedImage icon)
    {
        super(2000,550,width,height,icon,"Projectiles.Tractor");
    }
    public void moveLeft(int x)
    {
        super.setPos(getxVal()-x,getyVal());
    }
}
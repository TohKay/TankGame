package tankwars;

import java.awt.*;
import java.awt.image.BufferedImage;
import static tankwars.KeyInputOne.direction;
import java.awt.geom.AffineTransform;

public class Tank extends GameObject{
    
    
    Handler handler;
    TankWars tankwars;
    private BufferedImage tankSpriteUp, tankSpriteDown, tankSpriteLeft, tankSpriteRight, tankSpriteUpRight, tankSpriteDownRight, tankSpriteUpLeft, tankSpriteDownLeft;
    
    public Tank(int x, int y, ID id, Handler handler, SpriteSheet tankOne_ss, TankWars tankwars) {
        super(x, y, id, tankOne_ss);
        this.handler = handler;
        this.tankwars = tankwars;
        tankSpriteRight = tankOne_ss.chooseTank(1, 1, 64, 64);
        tankSpriteUp = tankOne_ss.chooseTank(1, 4, 64, 64);
        tankSpriteLeft = tankOne_ss.chooseTank(1, 7, 64, 64);
        tankSpriteDown = tankOne_ss.chooseTank(1, 10, 64, 64);
    }
    
    public void tick() {
        x += velX;
        y += velY;
        collision();
        
        if(handler.isUp()){
            velY = -5;
        } else if(!handler.isDown()){
            velY = 0;
        }
        
        if(handler.isDown()){
            velY = 5;
        } else if(!handler.isUp()){
            velY = 0;
        }
        
        if(handler.isLeft()){
            velX = -5;
        } else if(!handler.isRight()){
            velX = 0;
        }
        
        if(handler.isRight()){
            velX = 5;
        } else if(!handler.isLeft()){
            velX = 0;
        }
    }
    
    public void collision(){
        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);
            
            if(tempObject.getId() == ID.UnbreakableWall){
                if(getBounds().intersects(tempObject.getBounds())){
                    x += velX * -1;
                    y += velY * -1;
                }
            }   
            
            if(tempObject.getId() == ID.BreakableWall){
                if(getBounds().intersects(tempObject.getBounds())){
                    x += velX * -1;
                    y += velY * -1;
                }
            }
            
            if(tempObject.getId() == ID.PlayerTwo){
                if(getBounds().intersects(tempObject.getBounds())){
                    x += velX * -1;
                    y += velY * -1;
                }
            } 
        
            if(tempObject.getId() == ID.HealthBox){
                if(getBounds().intersects(tempObject.getBounds())){
                    if(tankwars.tankOneHealth <= 70){
                        tankwars.tankOneHealth += 30;
                    } else {
                        tankwars.tankOneHealth = 100;
                    }
                    handler.removeObject(tempObject);
                }
            }
        }
    }

    // Draws the tank
    public void render(Graphics g) {
        if(direction == 1){
            g.drawImage(tankSpriteUp, x, y, null);
        } else if(direction == 2){
            g.drawImage(tankSpriteDown, x, y, null);
        } else if(direction == 3){
            g.drawImage(tankSpriteLeft, x, y, null);
        } else if(direction == 4){
            g.drawImage(tankSpriteRight, x, y, null);
        } else {
            g.drawImage(tankSpriteUp, x, y, null);
        }
    }

    // TODO: Fix Affine Transform so tank rotates smoothly
    /*
    public void render(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        AffineTransform at = AffineTransform.getTranslateInstance(x,y);
        *//*at = new AffineTransform(1,0,0,1,0,0);
        at.translate(50,50);
        g2.setTransform(at);*//*
        if(direction == 1){
            //at = new AffineTransform(1,0,0,1,0,0);
            at.rotate(Math.toRadians(90));
            g2.drawImage(tankSpriteUp, at, null);
        } else if(direction == 2){
            //at = new AffineTransform(0,0,0,0,0,0);
            at.rotate(Math.toRadians(-90));
            g2.drawImage(tankSpriteDown, at, null);
        } else if(direction == 3){
            //at = new AffineTransform(-1,0,0,-1,0,0);
            at.rotate(Math.toRadians(90));
            g2.drawImage(tankSpriteLeft, at, null);
        } else if(direction == 4){
            //at = new AffineTransform(1,0,0,1,0,0);
            at.rotate(Math.toRadians(-90));
            g2.drawImage(tankSpriteRight, at, null);
        } else {
            g.drawImage(tankSpriteUp, x, y, null);
        }
    }*/

    // Bounds used for collisions
    public Rectangle getBounds() {
        return new Rectangle(x, y, 55, 55);
    }
}

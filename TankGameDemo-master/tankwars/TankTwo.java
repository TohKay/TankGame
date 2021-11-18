package tankwars;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import static tankwars.KeyInputTwo.direction;

public class TankTwo extends GameObject{
    
    Handler handler;
    TankWars tankwars;
    private BufferedImage tankTwoSpriteUp, tankTwoSpriteRight, tankTwoSpriteLeft, tankTwoSpriteDown;
    
    public TankTwo(int x, int y, ID id, Handler handler, SpriteSheet tankTwo_ss, TankWars tankwars) {
        super(x, y, id, tankTwo_ss);
        this.handler = handler;
        this.tankwars = tankwars;
        tankTwoSpriteRight = tankTwo_ss.chooseTank(1, 1, 64, 64);
        tankTwoSpriteUp = tankTwo_ss.chooseTank(1, 4, 64, 64);
        tankTwoSpriteLeft = tankTwo_ss.chooseTank(1, 7, 64, 64);
        tankTwoSpriteDown = tankTwo_ss.chooseTank(1, 10, 64, 64);

    }
    
    public void tick() {
        x += velX;
        y += velY;
        collision();
        
        if(handler.isUpTwo()){
            velY = -5;
        } else if(!handler.isDownTwo()){
            velY = 0;
        }
        
        if(handler.isDownTwo()){
            velY = 5;
        } else if(!handler.isUpTwo()){
            velY = 0;
        }
        
        if(handler.isLeftTwo()){
            velX = -5;
        } else if(!handler.isRightTwo()){
            velX = 0;
        }
        
        if(handler.isRightTwo()){
            velX = 5;
        } else if(!handler.isLeftTwo()){
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
            
            if(tempObject.getId() == ID.PlayerOne){
                if(getBounds().intersects(tempObject.getBounds())){
                    x += velX * -1;
                    y += velY * -1;
                }
            } 
            
            if(tempObject.getId() == ID.HealthBox){
                if(getBounds().intersects(tempObject.getBounds())){
                    if(tankwars.tankTwoHealth <= 70){
                        tankwars.tankTwoHealth += 30;
                    } else {
                        tankwars.tankTwoHealth = 100;
                    }
                    handler.removeObject(tempObject);
                }
            }
            /*
            if(tempObject.getId() == ID.Bullet){
                if(getBounds().intersects(tempObject.getBounds())){
                    tankwars.tankTwoHealth -= 15;
                    handler.removeObject(this);
                }
            }*/
        }
    }
    

    // Draws the tank
    public void render(Graphics g) {
        if(direction == 1){
            g.drawImage(tankTwoSpriteUp, x, y, null);
        } else if(direction == 2){
            g.drawImage(tankTwoSpriteDown, x, y, null);
        } else if(direction == 3){
            g.drawImage(tankTwoSpriteLeft, x, y, null);
        } else if(direction == 4){
            g.drawImage(tankTwoSpriteRight, x, y, null);
        } else {
            g.drawImage(tankTwoSpriteUp, x, y, null);
        }
    }

    // Bounds used for collisions
    public Rectangle getBounds() {
        return new Rectangle(x, y, 55, 55);
    }
}

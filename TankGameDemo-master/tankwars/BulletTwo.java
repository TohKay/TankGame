package tankwars;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class BulletTwo extends GameObject {

    private TankWars tankwars;
    private Handler handler;
    private final int direction;
    
    public BulletTwo(int x, int y, ID id, Handler handler, SpriteSheet ss, int direction, TankWars tankwars) {
        super(x, y, id, ss);
        this.handler = handler;
        this.tankwars = tankwars;
        this.direction = direction;
    }
    
    public void tick() {
        x += velX;
        y += velY;
        collision();
        
        switch (direction){
        case 1:
            y -= 10;
            break;
        case 2:
            y += 10;
            break;
        case 3:
            x -= 10;
            break;
        case 4:
            x += 10;
            break;
        }
        
    }
    
    public void collision(){
        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);
            
            if(tempObject.getId() == ID.UnbreakableWall){
                if(getBounds().intersects(tempObject.getBounds())){
                    handler.removeObject(this);
                }
            }
            
            if(tempObject.getId() == ID.BreakableWall){
                if(getBounds().intersects(tempObject.getBounds())){
                    handler.removeObject(this);
                }
            }
            
            if(tempObject.getId() == ID.PlayerOne){
                if(getBounds().intersects(tempObject.getBounds())){
                    if(tankwars.tankOneHealth >= 16){
                        tankwars.tankOneHealth -= 15;
                    } else if(tankwars.tankOneLives >= 2){
                        tankwars.tankOneLives -= 1;
                        tankwars.tankOneHealth = 100;
                    } else {
                        tankwars.tankOneLives -= 1;
                        tankwars.tankOneHealth = 0;
                        handler.removeObject(tempObject);
                    }
                    handler.removeObject(this);
                }
            }
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.black);
        g.fillOval(x, y, 12, 12);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 12, 12);
    }
}

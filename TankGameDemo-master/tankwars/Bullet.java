package tankwars;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Bullet extends GameObject {

    private TankWars tankwars;
    private Handler handler;
    private final int direction;
    
    public Bullet(int x, int y, ID id, Handler handler, SpriteSheet ss, int direction, TankWars tankwars) {
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
            
            if(tempObject.getId() == ID.PlayerTwo){
                if(getBounds().intersects(tempObject.getBounds())){
                    tankwars.tankTwoHealth -= 15;
                    handler.removeObject(this);
                }
            }
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.black);
        g.fillOval(x, y, 8, 8);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 8, 8);
    }
}

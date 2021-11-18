package tankwars;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class BreakableWall extends GameObject{
    
    private BufferedImage wallTwo_image;
    private Handler handler;

    public BreakableWall(int x, int y, ID id, Handler handler, SpriteSheet ss) {
        super(x, y, id, ss);
        
        this.handler = handler;
        wallTwo_image = ss.chooseWall(1, 1, 32, 32);
    }

    public void tick() {
        
    }

    public void collision() {
        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);
            
            if(tempObject.getId() == ID.WallBulletOne || tempObject.getId() == ID.WallBulletTwo){
                if(getBounds().intersects(tempObject.getBounds())){
                    handler.removeObject(tempObject);
                }
            }
        }
    }
    
    public void render(Graphics g) {
        g.drawImage(wallTwo_image, x, y, null);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }
    
}

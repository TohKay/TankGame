package tankwars;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class UnbreakableWall extends GameObject{

    private BufferedImage wallOne_image;

    public UnbreakableWall(int x, int y, ID id, SpriteSheet ss) {
        super(x, y, id, ss);
    
        wallOne_image = ss.chooseWall(1, 1, 32, 32);
    }
    
    public void collision(){
        
    }
    
    public void tick(){
        
    }
    
    // Draws the wall
    public void render(Graphics g){
        g.drawImage(wallOne_image, x, y, null);
    }
    
    // Sets bounds for collision
    public Rectangle getBounds(){
        return new Rectangle(x, y, 32, 32);
    }
}

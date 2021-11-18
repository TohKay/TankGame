package tankwars;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class HealthBox extends GameObject{
    
    private BufferedImage healthSprite;

    public HealthBox(int x, int y, ID id, SpriteSheet healthSprite_ss) {
        super(x, y, id, healthSprite_ss);
        
        healthSprite = healthSprite_ss.chooseWall(1, 1, 32, 32);
    }

    public void collision(){
        
    }
    
    public void tick() {
        
    }

    public void render(Graphics g) {
        g.drawImage(healthSprite, x, y, null);
    }

    public Rectangle getBounds() {
        return new Rectangle(x,y,32,32);
    }

    
    
}

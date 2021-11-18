package tankwars;

import java.awt.image.BufferedImage;

public class SpriteSheet {

    private BufferedImage image;
    
    public SpriteSheet(BufferedImage image){
        this.image = image;
    }
    
    public BufferedImage chooseTank(int column, int row, int width, int height){
        return image.getSubimage((column * 64) - 64, (row * 64) - 64, width, height);
    }
    
    public BufferedImage chooseBackground(int column, int row, int width, int height){
        return image.getSubimage((column * 320) - 320, (row * 240) - 240, width, height);
    }
    
    public BufferedImage chooseWall(int column, int row, int width, int height){
        return image.getSubimage((column * 32) - 32, (row * 32) - 32, width, height);
    }
}

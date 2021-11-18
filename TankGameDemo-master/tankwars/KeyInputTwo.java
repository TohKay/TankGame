package tankwars;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInputTwo extends KeyAdapter{
    
    private Handler handler;
    private Camera camera;
    private TankWars tankwars;
    public static int direction;

    public KeyInputTwo(Handler handler, Camera camera, TankWars tankwars) {
        this.handler = handler;
        this.camera = camera;
        this.tankwars = tankwars;
    }
    
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        SpriteSheet ss = null;
        
        // Loops through every object
        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);
            
            // This is when it hits PlayerTwo's object
            if(tempObject.getId() == ID.PlayerTwo){
                if(key == KeyEvent.VK_UP) {
                    handler.setUpTwo(true);
                    direction = 1;
                }
                if(key == KeyEvent.VK_DOWN) {
                    handler.setDownTwo(true);
                    direction = 2;
                }
                if(key == KeyEvent.VK_LEFT) {
                    handler.setLeftTwo(true);
                    direction = 3;
                }
                if(key == KeyEvent.VK_RIGHT) {
                    handler.setRightTwo(true);
                    direction = 4;
                }
                if(key == KeyEvent.VK_SLASH) 
                    handler.addObject(new BulletTwo(tempObject.getX()+32, tempObject.getY()+32, ID.BulletTwo, handler, ss, direction, tankwars));
                if(key == KeyEvent.VK_PERIOD) 
                    handler.addObject(new WallBulletTwo(tempObject.getX()+32, tempObject.getY()+32, ID.WallBulletTwo, handler, ss, direction, tankwars));
            }
        }
    }
    
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        
        // Loops through every object
        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);
            
            // This is when it hits PlayerTwo's object
            if(tempObject.getId() == ID.PlayerTwo){
                if(key == KeyEvent.VK_UP) handler.setUpTwo(false);
                if(key == KeyEvent.VK_DOWN) handler.setDownTwo(false);
                if(key == KeyEvent.VK_LEFT) handler.setLeftTwo(false);
                if(key == KeyEvent.VK_RIGHT) handler.setRightTwo(false);
            }
        }
    }
}

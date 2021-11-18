package tankwars;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInputOne extends KeyAdapter{

    private Handler handler;
    private Camera camera;
    private TankWars tankwars;
    public static int direction;
    
    public KeyInputOne(Handler handler, Camera camera, TankWars tankwars){
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
            
            // This is when it hits PlayerOne's object
            if(tempObject.getId() == ID.PlayerOne){
                if(key == KeyEvent.VK_W) {
                    handler.setUp(true);
                    direction = 1;
                }
                if(key == KeyEvent.VK_S) {
                    handler.setDown(true);
                    direction = 2;
                }
                if(key == KeyEvent.VK_A) {
                    handler.setLeft(true);
                    direction = 3;
                }
                if(key == KeyEvent.VK_D) {
                    handler.setRight(true);
                    direction = 4;
                }
                if(key == KeyEvent.VK_G) 
                    handler.addObject(new BulletOne(tempObject.getX()+32, tempObject.getY()+32, ID.BulletOne, handler, ss, direction, tankwars));
                if(key == KeyEvent.VK_F) 
                    handler.addObject(new WallBulletOne(tempObject.getX()+32, tempObject.getY()+32, ID.WallBulletOne, handler, ss, direction, tankwars));
            }
            /*
            // This is when it hits PlayerTwo's object
            
            
        }
        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);
            
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
                if(key == KeyEvent.VK_PERIOD) 
                    handler.addObject(new BulletTwo(tempObject.getX()+32, tempObject.getY()+32, ID.BulletTwo, handler, ss, direction, tankwars));
                if(key == KeyEvent.VK_SLASH) 
                    handler.addObject(new WallBulletTwo(tempObject.getX()+32, tempObject.getY()+32, ID.WallBulletTwo, handler, ss, direction, tankwars));
            }*/
        }
    }
    
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        
        // Loops through every object
        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);
            
            // This is when it hits PlayerOne's object
            if(tempObject.getId() == ID.PlayerOne){
                if(key == KeyEvent.VK_W) handler.setUp(false);
                if(key == KeyEvent.VK_S) handler.setDown(false);
                if(key == KeyEvent.VK_A) handler.setLeft(false);
                if(key == KeyEvent.VK_D) handler.setRight(false);
            }
            /*
            // This is when it hits PlayerTwo's object
            if(tempObject.getId() == ID.PlayerTwo){
                if(key == KeyEvent.VK_UP) handler.setUpTwo(false);
                if(key == KeyEvent.VK_DOWN) handler.setDownTwo(false);
                if(key == KeyEvent.VK_LEFT) handler.setLeftTwo(false);
                if(key == KeyEvent.VK_RIGHT) handler.setRightTwo(false);
            }*/
        }
    }
}

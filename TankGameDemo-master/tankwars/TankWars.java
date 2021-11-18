package tankwars;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TankWars extends Canvas implements Runnable{
    
    // Backbone
    private boolean isRunning = false;
    private Thread thread;
    private Handler handler;
    private Camera camera, camera2;
    
    // Sprite Sheets
    private SpriteSheet ss, tankOne_ss, tankTwo_ss, wallOne_ss, wallTwo_ss, healthBox_ss;
    
    // Buffered Images
    private BufferedImage level_skeleton, world, leftScreen, rightScreen, background, wallOne, wallTwo, tankOneSprite, tankTwoSprite, healthBox= null;
    
    public int tankOneHealth = 100;
    public int tankTwoHealth = 100;
    public int tankOneLives = 3;
    public int tankTwoLives = 3;

    
    public TankWars() {
        new GameWindow(1280, 960, "Tank Game", this);
        start();
        
        handler = new Handler();
        camera = new Camera(0, 0);
        camera2 = new Camera(0, 0);
        this.addKeyListener(new KeyInputOne(handler, camera, this));
        this.addKeyListener(new KeyInputTwo(handler, camera2, this));      // Attempt to allow both players to shoot simultaneously
        
        
        /************************************* 
                For testing purposes 
        *************************************/
        

        
        BufferedImageLoader loader = new BufferedImageLoader();
        //world = new BufferedImage(1280, 960, BufferedImage.TYPE_BYTE_INDEXED);
        //leftScreen = new BufferedImage(640, 480, BufferedImage.TYPE_INT_ARGB);
        //rightScreen = new BufferedImage(640, 480, BufferedImage.TYPE_INT_ARGB);
        
        // Load images
        level_skeleton = loader.loadImage("/tank_level.png");           // Loads solid color skeleton
        background = loader.loadImage("/background.png");               // Loads background image
        wallOne = loader.loadImage("/UnbreakableWall.png");             // Loads wall one image
        wallTwo = loader.loadImage("/BreakableWall.png");               // Loads wall two image
        tankOneSprite = loader.loadImage("/TankOneSpriteSheet.png");    // Loads tank one sprite sheet image
        tankTwoSprite = loader.loadImage("/TankTwoSpriteSheet.png");    // Loads tank two sprite sheet image
        healthBox = loader.loadImage("/HealthBox.png");
        
        ss = new SpriteSheet(background);                               // background sprite sheet
        tankOne_ss = new SpriteSheet(tankOneSprite);                    // tankOne's sprite sheet
        tankTwo_ss = new SpriteSheet(tankTwoSprite);                    // tankTwo's sprite sheet
        wallOne_ss = new SpriteSheet(wallOne);                          // wallOne's sprite sheet
        wallTwo_ss = new SpriteSheet(wallTwo);                          // wallTwo's sprite sheet
        healthBox_ss = new SpriteSheet(healthBox);                      // ammoCrate's sprite sheet
        
        
        
        background = ss.chooseBackground(1, 1, 320, 240);               // Grabs background image
        
        loadLevel(level_skeleton);
    }
    
    // Starts thread
    private void start(){
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }
    
    // Stops thread
    private void stop(){
        isRunning = false;
        try {
            thread.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(TankWars.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // Game Loop
    public void run(){
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(isRunning){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                tick();
                delta--;
            }
            render();
            frames++;
            
            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                frames = 0;
            }
        }
        stop();
    }
    
    // Updates everything in game
    public void tick(){
        
        for(int i = 0; i < handler.object.size(); i++){
            if(handler.object.get(i).getId() == ID.PlayerOne){
                camera.tick(handler.object.get(i));
            }
        }
        handler.tick();
    }
    
    // Renders everything in game
    public void render(){
        BufferStrategy bufferStrategy = this.getBufferStrategy();
        if(bufferStrategy == null){
            this.createBufferStrategy(3);
            return;
        }
        
        Graphics g = bufferStrategy.getDrawGraphics();
        Graphics2D g2d = (Graphics2D) g;
        //Graphics2D g3 = world.createGraphics();
        
        /***************************************
                    Begin Drawing 
        ***************************************/
        g2d.translate(-camera.getX(), -camera.getY());      // Following camera -- Place everything drawn in between
        
        
        for(int xx = 0; xx < 32*180; xx += 320){             // This for-loop creates the background image
            for(int yy = 0; yy < 32*110; yy += 240){         // using background.png.
                g.drawImage(background, xx, yy, null);
            }
        }
        
        //handler.render(g3);
        handler.render(g);                                  // Must be placed after background ** Don't forget **
        g2d.translate(camera.getX(), camera.getY());        // Following camera -- Place everything drawn in between
        
        //leftScreen = world.getSubimage((int)camera.getX(), (int)camera.getY(), 960, 960);
        //rightScreen = world.getSubimage((int)camera2.getX(), (int)camera.getY(), 960, 960);
        
        //g2d.drawImage(leftScreen, 0, 0, 960, 1080, null);
        //g2d.drawImage(rightScreen, 960, 0, 960, 1080, null);
        
        // Draws display
        
        // Draw Player One Display
        // Health Bar
        g.setColor(Color.black);
        g.fillRect(5, 5, 100, 32);
        g.setColor(Color.green);
        g.fillRect(5, 5, tankOneHealth, 32);
        g.setColor(Color.black);
        g.drawRect(5, 5, 100, 32);
        g.setColor(Color.white);        
        
        // Text Display for Player One
        g.drawString("Player One", 10, 50);
        g.drawString("Health: " + tankOneHealth, 10, 65);
        g.drawString("Lives: " + tankOneLives, 10, 80);
        
        // Draw Player Two Display
        // Health Bar
        g.setColor(Color.black);
        g.fillRect(855, 5, 100, 32);
        g.setColor(Color.green);
        g.fillRect(855, 5, tankTwoHealth, 32);
        g.setColor(Color.black);
        g.drawRect(855, 5, 100, 32);
        g.setColor(Color.white);
        
        // Text Display for Player Two
        g.drawString("Player Two", 855, 50);
        g.drawString("Health: " + tankTwoHealth, 855, 65);
        g.drawString("Lives: " + tankTwoLives, 855, 80);
        
        
        
        

        /***************************************
                      End Drawing
        ***************************************/
        
        g.dispose();
        bufferStrategy.show();
    }
    
    // Loads the level
    private void loadLevel(BufferedImage image){
        int w = image.getWidth();
        int h = image.getHeight();
        
        for(int xx = 0; xx < w; xx++){
            for(int yy = 0; yy < h; yy++){
                int pixel = image.getRGB(xx,yy);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;
                
                if(green == 255 && blue == 0 && red == 0)
                    handler.addObject(new TankTwo(xx*64, yy*64, ID.PlayerTwo, handler, tankTwo_ss, this));
                if(blue == 255 && green == 0 && red == 0)
                    handler.addObject(new Tank(xx*64, yy*64, ID.PlayerOne, handler, tankOne_ss, this));
                if(red == 255 && green == 0 && blue == 0)
                    handler.addObject(new UnbreakableWall(xx*32, yy*32, ID.UnbreakableWall, wallOne_ss));
                if(green == 255 && blue ==  255 && red == 0)
                    handler.addObject(new BreakableWall(xx*32, yy*32, ID.BreakableWall, handler, wallTwo_ss));
                if(red == 255 && green == 255 && blue == 0)
                    handler.addObject(new HealthBox(xx*32, yy*32, ID.HealthBox, healthBox_ss));
            }
        }
    }
    
    public static void main(String[] args) {

        new TankWars();
        
    }
}

package tankwars;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {

    protected int x, y;                     // Object's Position    
    protected float velX = 0, velY = 0;     // Object's Speed
    protected ID id;                        // Object's ID
    protected SpriteSheet ss;               // Object's Spritesheet

    public GameObject(int x, int y, ID id, SpriteSheet ss) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.ss = ss;
    }

    public abstract void tick();                // Updates object
    public abstract void render(Graphics g);    // Draws object
    public abstract void collision();           // Detects collision
    public abstract Rectangle getBounds();      // Collision detection bounds
    
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public float getVelX() {
        return velX;
    }

    public void setVelX(float velX) {
        this.velX = velX;
    }

    public float getVelY() {
        return velY;
    }

    public void setVelY(float velY) {
        this.velY = velY;
    }

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }
    
}

package tankwars;

public class Camera {

    private float x;
    private float y;

    public Camera(float x, float y) {
        this.x = x;
        this.y = y;
    }
    
    public void tick(GameObject object){
        // Camera Coordinates
        //x = object.getX() - 1280/2;       // Less movement from camera
        //y = object.getY() - 960/2;
        x += ((object.getX() - x) - 1280/2) * 0.05f;        
        y += ((object.getY() - y) - 960/2) * 0.05f;     
        
        // Set Camera Bounds
        if(x <= 0)
            x = 0;
        if(x >= 2175)
            x = 2175;
        if(y <= 0)
            y = 0;
        if(y >= 960)
            y = 960;
        
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}

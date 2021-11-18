package tankwars;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;

public class Handler {
    
    ArrayList<GameObject> object = new ArrayList<GameObject>();
    
    private boolean up, down, left, right = false;
    private boolean upTwo, downTwo, leftTwo, rightTwo = false;
    
    // Updates all game objects
    public void tick(){
        for(int i = 0; i < object.size(); i++){
            GameObject tempObject = object.get(i);
            
            tempObject.tick();
        }
    }
    
    // Draws all objects
    public void render(Graphics g){
        for(int i = 0; i < object.size(); i++){
            GameObject tempObject = object.get(i);
            
            tempObject.render(g);
        }
    }
    
    public void addObject(GameObject tempObject){
        object.add(tempObject);
    }
    
    public void removeObject(GameObject tempObject){
        object.remove(tempObject);
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isUpTwo() {
        return upTwo;
    }

    public void setUpTwo(boolean upTwo) {
        this.upTwo = upTwo;
    }

    public boolean isDownTwo() {
        return downTwo;
    }

    public void setDownTwo(boolean downTwo) {
        this.downTwo = downTwo;
    }

    public boolean isLeftTwo() {
        return leftTwo;
    }

    public void setLeftTwo(boolean leftTwo) {
        this.leftTwo = leftTwo;
    }

    public boolean isRightTwo() {
        return rightTwo;
    }

    public void setRightTwo(boolean rightTwo) {
        this.rightTwo = rightTwo;
    }

}

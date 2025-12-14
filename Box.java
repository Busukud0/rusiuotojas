import java.awt.*;

public class Box extends GameObject implements Collidable {
    
    public Box(int x, int y, int size, Color color) {
        super(x, y, size, color);
    }
    
    @Override
    public void draw(Graphics2D g) {
        g.setColor(color);
        g.fillRect((int)x, (int)y, size, size);
    }
    
    @Override
    public void update(double dt) {
        // Box nejuda
    }
    
    @Override
    public boolean checkCollision(double pointX, double pointY) {
        return pointX > x && pointX < x + size;
    }
    
    //getters
    public int getCenterX() { return (int)(x + size / 2); }
}
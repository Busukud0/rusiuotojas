import java.awt.Color;
import java.awt.Graphics2D;

public abstract class GameObject {
    protected double x, y;
    protected int size;
    protected Color color;
    
    public GameObject(double x, double y, int size, Color color) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.color = color;
    }
    
    public abstract void update(double dt);
    public abstract void draw(Graphics2D g);
    
    public double getX() { return x; }
    public double getY() { return y; }
    public int getSize() { return size; }
    public Color getColor() { return color; }
}
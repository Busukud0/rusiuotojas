import java.awt.*;

public class Box {
    private int x, y, size;
    private Color color;

    public Box(int x, int y, int size, Color color) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.color = color;
    }

    public void draw(Graphics2D g) {
        g.setColor(color);
        g.fillRect(x, y, size, size);
    }

    public int getCenterX() {
        return x + size / 2;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public int getSize() {
        return size;
    }

    public Color getColor() {
        return color;
    }
}

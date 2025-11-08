import java.awt.*;

public class Ball {
    private double x, y, prevY;
    private int size;
    private double vy = 200;
    private double vx = 0;
    private Color color;

    public Ball(double x, double y, int size, Color color, double multiplier) {
        this.x = x - size/2.0;
        this.y = y - size/2.0;
        this.size = size;
        this.color = color;
        this.prevY = y;
        this.vy *= multiplier;
    }

    public void update(double dt) {
        prevY = y;
        y += vy * dt;
        x += vx * dt;
    }

    public void draw(Graphics2D g) {
        g.setColor(color);
        g.fillOval((int)x, (int)y, size, size);
    }

    public double getCenter() {
        return x + size / 2.0;
    }

    public void setVx(double vx) {
        this.vx = vx;
    }

    public void setVy(double vy) {
        this.vy = vy;
    }

    public void setAngle(double angleDegrees) {
        double totalSpeed = vy*3;
        double angleRad = Math.toRadians(angleDegrees);
        vx = totalSpeed * Math.sin(angleRad);
        vy = totalSpeed * Math.cos(angleRad);
    }

    public double getVy() {
        return vy;
    }

    public double getY() {
        return y;
    }
    public double getX() {
        return x;
    }

    public double getPrevY() {
        return prevY;
    }

    public Color getColor() {
        return color;
    }

}

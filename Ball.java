import java.awt.*;

public class Ball extends GameObject {
    private double prevY;
    private double vy = 200;
    private double vx = 0;

    public Ball(double x, double y, int size, Color color, double multiplier) {
        super(x - size/2.0, y - size/2.0, size, color);
        this.prevY = y;
        this.vy *= multiplier;
    }

    @Override
    public void update(double dt) {
        prevY = y;
        y += vy * dt;
        x += vx * dt;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(color);
        g.fillOval((int)x, (int)y, size, size);
    }

    public void setAngle(double angleDegrees) {
        double totalSpeed = vy*3;
        double angleRad = Math.toRadians(angleDegrees);
        vx = totalSpeed * Math.sin(angleRad);
        vy = totalSpeed * Math.cos(angleRad);
    }

    //getters
    public double getCenter() { return x + size / 2.0; }
    public double getVy() { return vy; }
    public double getPrevY() { return prevY; }
}

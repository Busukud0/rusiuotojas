import java.awt.Color;
import java.util.Random;

public class BallFactory {
    private static final Color[] ballColors = {Color.RED, Color.GREEN, Color.BLUE, Color.GRAY};
    private static final int ballSize = 40;
    
    public static Ball createBall(double x, double y, double speedMultiplier) {
        Color randomColor = getRandomColor();
        return new Ball(x, y, ballSize, randomColor, speedMultiplier);
    }
    
    private static Color getRandomColor() {
        return ballColors[new Random().nextInt(ballColors.length)];
    }
}
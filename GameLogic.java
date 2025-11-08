import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.*;


public class GameLogic {
    public static final int scrWidth = 500;
    public static final int scrHeight = 500;
    public int getScreenWidth() { return scrWidth; }
    public int getScreenHeight() { return scrHeight; }

    private final int boxSize = 120;
    private final int topY = 50;
    private final int bottomY = 350;

    private static final GameLogic instance = new GameLogic();

    private double score, scoreMultiplier, ballSpeedMultiplier, maxScoreMultiplier=0;
    private int lives, lever, highScore=0;
    private boolean isPaused, showWelcomeMenu=true;

    private Ball ball;
    private final List<Box> boxes = new ArrayList<>();

    private GameLogic() {
        boxes.add(new Box(gap(), bottomY, boxSize, Color.RED));
        boxes.add(new Box(gap() + boxSize + gap(), bottomY, boxSize, Color.GREEN));
        boxes.add(new Box(gap() + boxSize + gap() + boxSize + gap(), bottomY, boxSize, Color.BLUE));

        spawnBall();
    }

    public static GameLogic getInstance() { return instance; }

    public void handleKeyPress(char key) {
        switch (key) {
            case '1' -> { lever = 0; }
            case '2' -> { lever = 1; }
            case '3' -> { lever = 2; }
        }
    }

    public void restartGame() {
        score = 0;
        lives = 3;
        scoreMultiplier = 1;
        ballSpeedMultiplier = 1;
        lever = 1;
        spawnBall();
        isPaused = false;
        showWelcomeMenu = false;
    }

    public boolean isPaused() {
        return isPaused;
    }
    public boolean showWelcomeMenu() {
        return showWelcomeMenu;
    }

    private void spawnBall() {
        ball = new Ball(scrWidth / 2.0, 0, 40, randomBallColor(), ballSpeedMultiplier);
    }

    public void addBox(Box box) { boxes.add(box); }

    public int getHighScore() { return highScore; }
    public double getMaxScoreMultiplier() { return maxScoreMultiplier; }
    public int getLever() { return lever; }
    public Ball getBall() { return ball; }
    public List<Box> getBoxes() { return boxes; }
    public int getLives() { return lives; }
    public double getScore() { return score; }
    public double getScoreMultiplier() { return scoreMultiplier; }


    public void update(double dt) {
        ball.update(dt);

        if(lives<=0) {
            isPaused = true;
        }

        // lever effect at Y = 190
        if (ball.getPrevY() < 180 && ball.getY() >= 180) {
            switch (lever) {
                case 0 -> ball.setAngle(-46);
                case 2 -> ball.setAngle(46);
                default -> ball.setAngle(0);
            }
        }

        if (ball.getPrevY() < bottomY && ball.getY() >= bottomY) {
            for(int i=0; i<boxes.size(); i++) {
                if(ball.getX()>boxes.get(i).getX() && ball.getX()<boxes.get(i).getX()+boxes.get(i).getSize()) { 
                    if(boxes.get(i).getColor() == ball.getColor())
                    {
                        score += 1 * scoreMultiplier;
                        scoreMultiplier *= 1.05;
                        ballSpeedMultiplier*=1.02;
                    }
                    else {
                        lives -= 1;
                        if(scoreMultiplier>maxScoreMultiplier) maxScoreMultiplier=scoreMultiplier;
                        scoreMultiplier = 1;
                    }
                }
            }
            spawnBall();
        }
        if (score > highScore) highScore = (int)score;
    }

    public void draw(Graphics2D g2D) {
        for(Box box : boxes) box.draw(g2D);
        ball.draw(g2D);

        switch (lever) {
            case 0 -> {
                g2D.setColor(Color.RED);
                g2D.drawLine(scrWidth / 2 + lineOffset(), 200, scrWidth / 2 - lineOffset(), 200 + lineOffset() * 2);
            }
            case 1 -> {
                g2D.setColor(Color.GREEN);
                g2D.drawLine(scrWidth / 2 - lineOffset(), 200, scrWidth / 2 - lineOffset(), 200 + lineOffset() * 2);
                g2D.drawLine(scrWidth / 2 + lineOffset(), 200, scrWidth / 2 + lineOffset(), 200 + lineOffset() * 2);
            }
            case 2 -> {
                g2D.setColor(Color.BLUE);
                g2D.drawLine(scrWidth / 2 - lineOffset(), 200, scrWidth / 2 + lineOffset(), 200 + lineOffset() * 2);
            }
        }

        g2D.setColor(Color.WHITE);
        // upper middle lines
        g2D.drawLine(scrWidth / 2 - lineOffset(), topY, scrWidth / 2 - lineOffset(), 200);
        g2D.drawLine(scrWidth / 2 + lineOffset(), topY, scrWidth / 2 + lineOffset(), 200);

        // left lines
        g2D.drawLine(scrWidth / 2 - lineOffset(), 200, leftX() - lineOffset(), bottomY);
        g2D.drawLine(scrWidth / 2 - lineOffset(), 200 + lineOffset() * 2, leftX() + lineOffset(), bottomY);

        // lower middle lines
        g2D.drawLine(scrWidth / 2 - lineOffset(), 200 + lineOffset() * 2, middleX() - lineOffset(), bottomY);
        g2D.drawLine(scrWidth / 2 + lineOffset(), 200 + lineOffset() * 2, middleX() + lineOffset(), bottomY);

        // right lines
        g2D.drawLine(scrWidth / 2 + lineOffset(), 200 + lineOffset() * 2, rightX() - lineOffset(), bottomY);
        g2D.drawLine(scrWidth / 2 + lineOffset(), 200, rightX() + lineOffset(), bottomY);
    }

    private int lineOffset() { return boxSize / 4; }
    private int gap() { return (scrWidth - boxSize * 3) / 4; }
    private int leftX() { return gap() + boxSize / 2; }
    private int middleX() { return gap() + boxSize + gap() + boxSize / 2; }
    private int rightX() { return gap() + boxSize + gap() + boxSize + gap() + boxSize / 2; }

    private Color randomBallColor() {
        Color[] colors = { Color.RED, Color.GREEN, Color.BLUE };
        return colors[new Random().nextInt(colors.length)];
    }


}

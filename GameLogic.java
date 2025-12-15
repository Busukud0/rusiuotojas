import java.awt.Color;
import java.awt.Graphics2D;
import java.util.*;

public class GameLogic {
    private static final GameLogic instance = new GameLogic();

    public static final int scrWidth = 500;
    public static final int scrHeight = 500;
    private final int boxSize = 120;
    private final int topY = 50;
    private final int bottomY = 350;
    private final int leverY = 180;

    private double score, scoreMultiplier, ballSpeedMultiplier, maxScoreMultiplier=0, highScore=0, speedMultiplierLimit = 2;
    private int lives, lever;
    private boolean isPaused, showWelcomeMenu=true;

    private Ball ball;
    private final List<Box> boxes = new ArrayList<>();
    private final List<GameObject> allObjects = new ArrayList<>();
    private ScoringStrategy normalScoring = new NormalScoring();
    private ScoringStrategy bonusScoring = new BonusScoring();

    private GameLogic() {
        spawnBall();
        addBoxes();
    }

    public void restartGame() {
        boxes.clear();
        allObjects.clear();
        score = 0;
        lives = 3;
        scoreMultiplier = 1;
        ballSpeedMultiplier = 1;
        lever = 1;
        spawnBall();
        addBoxes();
        isPaused = false;
        showWelcomeMenu = false;
    }

    public void update(double dt) {
        if (ball == null) return;
        if(lives<=0) { isPaused = true; }

        ball.update(dt);
        if (ballReachedLineY(leverY)) { changeBallAngle(); }
        if (ballReachedLineY(bottomY)) {
            for(int i=0; i<boxes.size(); i++) {
                if(ballAndBoxCollide(i)) { 
                    if(boxesMatchColor(i)) {
                        if (ballSpeedMultiplier < speedMultiplierLimit) {
                            score = normalScoring.calculateScore(score, scoreMultiplier);
                            scoreMultiplier = normalScoring.updateMultiplier(scoreMultiplier);
                            ballSpeedMultiplier*=1.04;
                        } else {
                            score = bonusScoring.calculateScore(score, scoreMultiplier);
                            scoreMultiplier = bonusScoring.updateMultiplier(scoreMultiplier);
                        }
                        
                    }
                    else {
                        lives -= 1;
                        if(scoreMultiplier>maxScoreMultiplier) maxScoreMultiplier=scoreMultiplier;
                        scoreMultiplier = 1;
                        ballSpeedMultiplier -= (ballSpeedMultiplier - 1) / 2;
                    }
                }
            }
            spawnBall();
        }

        if (score > highScore) highScore = score;
    }

    public void draw(Graphics2D g2D) {
        drawLever(g2D);
        drawLines(g2D);
        if (ball != null) {
            ball.draw(g2D);
        }
        
        for(Box box : boxes) {
            box.draw(g2D);
        }
    }

    public void handleKeyPress(char key) {
        switch (key) {
            case '1' -> { lever = 0; }
            case '2' -> { lever = 1; }
            case '3' -> { lever = 2; }
        }
    }

    private void drawLines(Graphics2D g2D) {
        g2D.setColor(Color.WHITE);
        drawUpperMiddleLines(g2D);
        drawLeftLines(g2D);
        drawLowerMiddleLines(g2D);
        drawRightLines(g2D);
    }

    private void drawLever(Graphics2D g2D) {
        switch (lever) {
            case 0 -> drawLeverLeft(g2D);
            case 1 -> drawLeverMiddle(g2D);
            case 2 -> drawLeverRight(g2D);
        }
    }

    private void spawnBall() {
        if (ball != null) { allObjects.remove(ball); }
        ball = BallFactory.createBall(scrWidth / 2.0, 0, ballSpeedMultiplier);
        allObjects.add(0, ball);
    }

    private void addBoxes() {
        addBox(0, Color.RED);
        addBox(1, Color.GREEN);
        addBox(2, Color.BLUE);
    }

    private void addBox(int position, Color color) {
        Box box = new Box(gap() + position * (boxSize + gap()), bottomY, boxSize, color);
        boxes.add(box);
        allObjects.add(box);
    }

    private void drawUpperMiddleLines(Graphics2D g2D) {
        g2D.drawLine(scrWidth / 2 - lineOffset(), topY, scrWidth / 2 - lineOffset(), 200);
        g2D.drawLine(scrWidth / 2 + lineOffset(), topY, scrWidth / 2 + lineOffset(), 200);
    }
    private void drawLeftLines(Graphics2D g2D) {
        g2D.drawLine(scrWidth / 2 - lineOffset(), 200, leftX() - lineOffset(), bottomY);
        g2D.drawLine(scrWidth / 2 - lineOffset(), 200 + lineOffset() * 2, leftX() + lineOffset(), bottomY);
    }
    private void drawLowerMiddleLines(Graphics2D g2D) {
        g2D.drawLine(scrWidth / 2 - lineOffset(), 200 + lineOffset() * 2, middleX() - lineOffset(), bottomY);
        g2D.drawLine(scrWidth / 2 + lineOffset(), 200 + lineOffset() * 2, middleX() + lineOffset(), bottomY);
    }
    private void drawRightLines(Graphics2D g2D) {
        g2D.drawLine(scrWidth / 2 + lineOffset(), 200 + lineOffset() * 2, rightX() - lineOffset(), bottomY);
        g2D.drawLine(scrWidth / 2 + lineOffset(), 200, rightX() + lineOffset(), bottomY);
    }

    private void drawLeverLeft(Graphics2D g2D) {
        g2D.setColor(Color.RED);
        g2D.drawLine(scrWidth / 2 + lineOffset(), 200, scrWidth / 2 - lineOffset(), 200 + lineOffset() * 2);
    }

    private void drawLeverMiddle(Graphics2D g2D) {
        g2D.setColor(Color.GREEN);
        g2D.drawLine(scrWidth / 2 - lineOffset(), 200, scrWidth / 2 - lineOffset(), 200 + lineOffset() * 2);
        g2D.drawLine(scrWidth / 2 + lineOffset(), 200, scrWidth / 2 + lineOffset(), 200 + lineOffset() * 2);
    }

    private void drawLeverRight(Graphics2D g2D) {
        g2D.setColor(Color.BLUE);
        g2D.drawLine(scrWidth / 2 - lineOffset(), 200, scrWidth / 2 + lineOffset(), 200 + lineOffset() * 2);
    }

    private boolean ballReachedLineY(int lineY) {
        return ball.getPrevY() < lineY && ball.getY() >= lineY;
    }

    private void changeBallAngle() {
        switch (lever) {
            case 0 -> ball.setAngle(-46);
            case 2 -> ball.setAngle(46);
            default -> ball.setAngle(0);
        }
    }

    private boolean boxesMatchColor(int i) {
        return ball.getColor()==Color.GRAY || boxes.get(i).getColor() == ball.getColor();
    }

    private boolean ballAndBoxCollide(int i) {
        return boxes.get(i).checkCollision(ball.getX(), ball.getY());
    }

    //calculations
    private int lineOffset() { return boxSize / 4; }
    private int gap() { return (scrWidth - boxSize * 3) / 4; }
    private int leftX() { return gap() + boxSize / 2; }
    private int middleX() { return gap() + boxSize + gap() + boxSize / 2; }
    private int rightX() { return gap() + boxSize + gap() + boxSize + gap() + boxSize / 2; }

    // test helper
    public void setBall(double x, double y, Color color) {
        if (ball != null) allObjects.remove(ball);
        ball = new Ball(x, y, ball.getSize(), color, 1);
        allObjects.add(0, ball);
    }


    //GETTERS
    public boolean isPaused() { return isPaused; }
    public boolean showWelcomeMenu() { return showWelcomeMenu; }
    public double getHighScore() { return highScore; }
    public double getMaxScoreMultiplier() { return maxScoreMultiplier; }
    public int getLever() { return lever; }
    public int getLives() { return lives; }
    public double getScore() { return score; }
    public double getScoreMultiplier() { return scoreMultiplier; }
    public double getBallSpeedMultiplier() { return ballSpeedMultiplier; }
    public double getSpeedMultiplierLimit() { return speedMultiplierLimit; }
    public int getScreenWidth() { return scrWidth; }
    public int getScreenHeight() { return scrHeight; }
    public static GameLogic getInstance() { return instance; }
    public int getBottomY() { return bottomY; }
    public Ball getBall() { return ball; }
    public List<Box> getBoxes() { return Collections.unmodifiableList(boxes); }
}

import java.awt.Color;

public class UnitTests {

    public static void main(String[] args) {
        System.out.println("=== Starting Tests ===\n");

        testInitialSetup();
        testLeverPositions();
        testBallMovement();
        testScoreIncreaseOnHit();
        testMultiplierResetOnHit();

        System.out.println("\n=== Tests Finished ===");
    }

    public static void testInitialSetup() {
        int expHealth=3;
        double expScore=0, expScoreMultiplier=1;
        GameLogic game = setupTest("Initial variables... ");
        if (expHealth == game.getLives() && expScore == game.getScore() && expScoreMultiplier == game.getScoreMultiplier()) System.out.println("PASSED");
        else System.out.println("FAILED - Expected: " + expHealth + " " + expScore + " " + expScoreMultiplier + " " + ", Got: " + game.getLives() + " " + game.getScore() + " " + game.getScoreMultiplier());
    }

    public static void testLeverPositions() {
        GameLogic game = setupTest("Lever positions... ");
        char[] keys = {'1','2','3'};
        int[] expected = {0,1,2};
        boolean failed = false;

        for (int i = 0; i < keys.length; i++) {
            game.handleKeyPress(keys[i]);
            if (expected[i] != game.getLever()) {
                failed = true;
                System.out.println("FAILED - Expected: " + expected[i] + ", Got: " + game.getLever());
            }
        }
        if (!failed) System.out.println("PASSED");
    }

    public static void testBallMovement() {
        GameLogic game = setupTest("Ball moves... ");
        double y1 = game.getBall().getY();
        game.update(0.1);
        double y2 = game.getBall().getY();
        assertTrue(y2 != y1);
    }


    public static void testScoreIncreaseOnHit() {
        GameLogic game = setupTest("Score increases on hit... ");
        Box box = game.getBoxes().get(0); //red box

        double ballY = game.getBottomY() - 1;
        game.setBall(box.getCenterX(), ballY, Color.GRAY);

        double initialScore = game.getScore();
        game.update(1);

        assertTrue(game.getScore() > initialScore);
    }

    public static void testMultiplierResetOnHit() {
        GameLogic game = setupTest("Multiplier reset on hit... ");
        Box box = game.getBoxes().get(0); //red box

        //simulate successful hit
        double ballY1 = game.getBottomY() - 1;
        game.setBall(box.getCenterX(), ballY1, Color.GRAY);
        game.update(1);

        //simulate failed hit
        double ballY2 = game.getBottomY() - 1;
        game.setBall(box.getCenterX(), ballY2, Color.BLUE);
        double initialScoreMultiplier = game.getScoreMultiplier();
        game.update(1);

        assertTrue(game.getScoreMultiplier() < initialScoreMultiplier);
    }

    // helpers

    private static void assertTrue(boolean condition) {
        if (condition) System.out.println("PASSED");
        else System.out.println("FAILED");
    }

    private static GameLogic setupTest(String msg) {
        System.out.print(msg);
        GameLogic game = GameLogic.getInstance();
        game.restartGame();
        return game;
    }
}

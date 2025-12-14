import java.awt.Color;

public class UnitTests {

    public static void main(String[] args) {
        System.out.println("=== Starting Tests ===\n");

        testInitialLives();
        testInitialScore();
        testInitialMultiplier();

        testLeverLeft();
        testLeverMiddle();
        testLeverRight();

        testWelcomeMenuHiddenAfterRestart();
        testGameNotPausedAtStart();


        System.out.println("\n=== Tests Finished ===");
    }

    public static void testInitialLives() {
        GameLogic game = setupTest("Initial lives... ");
        assertEqual(3, game.getLives());
    }

    public static void testInitialScore() {
        GameLogic game = setupTest("Initial score... ");
        assertEqual(0, game.getScore());
    }

    public static void testInitialMultiplier() {
        GameLogic game = setupTest("Initial multiplier... ");
        assertEqual(1, (int)game.getScoreMultiplier());
    }

    public static void testLeverLeft() {
        GameLogic game = setupTest("Lever left... ");
        game.handleKeyPress('1');
        assertEqual(0, game.getLever());
    }

    public static void testLeverMiddle() {
        GameLogic game = setupTest("Lever middle... ");
        game.handleKeyPress('2');
        assertEqual(1, game.getLever());
    }

    public static void testLeverRight() {
        GameLogic game = setupTest("Lever right... ");
        game.handleKeyPress('3');
        assertEqual(2, game.getLever());
    }

    public static void testWelcomeMenuHiddenAfterRestart() {
        GameLogic game = setupTest("Welcome menu hidden... ");
        assertTrue(!game.showWelcomeMenu());
    }

    public static void testGameNotPausedAtStart() {
        GameLogic game = setupTest("Game not paused initially... ");
        assertTrue(!game.isPaused());
    }





    // helpers
    private static void assertEqual(int exp, int got) {
        if (exp == got) System.out.println("PASSED");
        else System.out.println("FAILED - Expected: " + exp + ", Got: " + got);
    }

    private static void assertEqual(double exp, double got) {
        if (exp == got) System.out.println("PASSED");
        else System.out.println("FAILED - Expected: " + exp + ", Got: " + got);
    }

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

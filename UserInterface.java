import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.Color;
import java.awt.BasicStroke;

public class UserInterface {

    private GameLogic game;
    public UserInterface(GameLogic game) {
        this.game = game;
    }

    private final char  restartKey = '4',
                        exitKey = '5';
    private Font    boldFont = new Font("Arial", Font.BOLD, 30),
                    plainFont = new Font("Arial", Font.PLAIN, 20),
                    scoreFont = new Font("Arial", Font.PLAIN, 80);

    public void handleKeyPress(char key)
    {
        if(game.isPaused() || game.showWelcomeMenu()) {
            switch(key) {
                case restartKey -> game.restartGame();
                case exitKey -> System.exit(0);
            }
        }
    }

    public void drawValues(Graphics2D g) {
       drawLives(g);
       drawScore(g);
       drawScoreMultiplier(g);
       drawSpeedMultiplier(g);
    }

    private void drawLives(Graphics2D g) {
        g.setFont(scoreFont);
        g.setColor(Color.PINK);
        g.drawString(String.valueOf(game.getLives()), 400, 150);
    }
    private void drawScore(Graphics2D g) {
        g.setFont(scoreFont);
        g.setColor(Color.WHITE);
        g.drawString(String.format("%.0f", game.getScore()), 50, 150);
    }
    private void drawScoreMultiplier(Graphics2D g) {
        g.setFont(plainFont);
        g.setColor(Color.YELLOW);
        g.drawString(String.format("%.2f", game.getScoreMultiplier()) + "X", 50, 180);
    }

    private void drawSpeedMultiplier(Graphics2D g) {
        g.setFont(plainFont);
        if(game.getBallSpeedMultiplier() < game.getSpeedMultiplierLimit()) {
            g.setColor(Color.GRAY);
            g.drawString(String.format("%.2f", game.getBallSpeedMultiplier()) + "X", 400, 180);
        } else {
            g.setColor(Color.WHITE);
            g.drawString("MAX", 400, 180);
        }
    }

    public void drawGameOverMenu(Graphics2D g) {
        drawCentered(g, "GAME OVER", 290, Color.RED, boldFont);
        drawCentered(g, "HIGH SCORE: " + String.format("%.0f", game.getHighScore()), 100, Color.WHITE, plainFont);
        drawCentered(g, "Press " + restartKey + " to restart", 340, Color.WHITE, plainFont);
        drawCentered(g, "Press " + exitKey + " to exit", 390, Color.WHITE, plainFont);
        drawCentered(g, "MAX SCORE MULTIPLIER: " + String.format("%.2f", game.getMaxScoreMultiplier()) + "X", 130, Color.GRAY, plainFont);
        drawCentered(g, String.format("%.0f", game.getScore()), 225, Color.GREEN, scoreFont);
    }

    public void drawWelcomeMenu(Graphics2D g) {
        drawCentered(g, "WELCOME!", 230, Color.BLUE, boldFont);
        drawCentered(g, "Press " + restartKey + " to start", 300, Color.WHITE, plainFont);
        drawCentered(g, "Press " + exitKey + " to exit", 350, Color.WHITE, plainFont);
    }

    private void drawCentered(Graphics2D g, String text, int y, Color color, Font font) {
        g.setFont(font);
        g.setColor(color);
        int width = g.getFontMetrics().stringWidth(text);
        g.drawString(text, (GameLogic.scrWidth - width) / 2, y);
    }

}



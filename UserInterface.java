import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.Color;
import java.awt.BasicStroke;

public class UserInterface {
    private GameLogic game = GameLogic.getInstance();
    private final char  restartKey = '1',
                        exitKey = '2';
    private Font    boldFont = new Font("Arial", Font.BOLD, 30),
                    plainFont = new Font("Arial", Font.PLAIN, 20),
                    scoreFont = new Font("Arial", Font.PLAIN, 80);

    public void update(double dt) {
        
    }

    public void handleKeyPress(char key)
    {
        if(game.isPaused() || game.showWelcomeMenu()) {
            switch(key) {
                case restartKey -> game.restartGame();
                case exitKey -> System.exit(0);
            }
        }
    }

    public void drawScore(Graphics2D g) {
       
        g.setFont(scoreFont);

        g.setColor(Color.PINK);
        g.drawString(String.valueOf(game.getLives()), 400, 150);

        g.setColor(Color.WHITE);
        g.drawString(String.format("%.0f", game.getScore()), 50, 150);

        g.setFont(plainFont);

        g.setColor(Color.GRAY);
        g.drawString(String.format("%.2f", game.getScoreMultiplier()) + "X", 50, 180);
    }

    public void drawGameOverMenu(Graphics2D g) {
        g.setFont(boldFont);
        g.setColor(Color.RED);

        drawCentered(g, "GAME OVER", 280);
        g.setColor(Color.WHITE);

        g.setFont(plainFont);

        drawCentered(g, "HIGH SCORE: " + String.format("%.0f", game.getHighScore()), 90);

        drawCentered(g, "Press " + restartKey + " to restart", 330);
        drawCentered(g, "Press " + exitKey + " to exit", 380);

        g.setColor(Color.GRAY);
        drawCentered(g, "HIGHEST MULTIPLIER: " + String.format("%.2f", game.getMaxScoreMultiplier()) + "X", 120);

        g.setFont(scoreFont);
        g.setColor(Color.GREEN);
        drawCentered(g, String.format("%.0f", game.getScore()), 215);

    }

    public void drawWelcomeMenu(Graphics2D g) {
        g.setColor(Color.BLUE);
        g.setFont(boldFont);
        drawCentered(g, "WELCOME!", 230);

        g.setColor(Color.WHITE);
        g.setFont(plainFont);
        drawCentered(g, "Press " + restartKey + " to start", 300);
        drawCentered(g, "Press " + exitKey + " to exit", 350);
    }

    private void drawCentered(Graphics2D g, String text, int y) {
        int width = g.getFontMetrics().stringWidth(text);
        g.drawString(text, (GameLogic.scrWidth - width) / 2, y);
    }



}



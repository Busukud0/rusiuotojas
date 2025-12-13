import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.Color;
import java.awt.BasicStroke;

public class MyPanel extends JPanel {
    private int lineSize = 3;
    private long lastTime;
    private GameLogic game;
    private UserInterface ui;

    public MyPanel(GameLogic game, UserInterface ui) {
        this.game = game;
        this.ui = ui;
        this.setPreferredSize(new Dimension(GameLogic.scrWidth, GameLogic.scrHeight));
        setBackground(new Color(30, 30, 30));
        startUpdateLoop();
    }

    private void startUpdateLoop() {
        lastTime = System.nanoTime();
        new Thread(this::gameLoop).start();
    }

    private void gameLoop() {
        while(true) {
            long now = System.nanoTime();
            double dt = (now - lastTime) / 1_000_000_000.0;
            lastTime = now;
            
            if(!game.isPaused()) game.update(dt);
            repaint();

            try { Thread.sleep(16); } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.setStroke(new BasicStroke(lineSize));
        drawGame(g2D);
    }

    private void drawGame(Graphics2D g2D) {
        if (game.showWelcomeMenu()) {
        ui.drawWelcomeMenu(g2D);
        } else if (!game.isPaused()) {
            game.draw(g2D);
            ui.drawValues(g2D);
        } else {
            ui.drawGameOverMenu(g2D);
        }
    }
}

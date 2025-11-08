import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.Color;
import java.awt.BasicStroke;

public class MyPanel extends JPanel {
    private long lastTime;
    private GameLogic game = GameLogic.getInstance();
    private UserInterface ui = new UserInterface();

    public MyPanel() {
        this.setPreferredSize(new Dimension(GameLogic.scrWidth, GameLogic.scrHeight));
        setBackground(Color.BLACK);
        
        // Start update loop
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
        g2D.setStroke(new BasicStroke(3));

        if (game.showWelcomeMenu()) {
        ui.drawWelcomeMenu(g2D);
        } else if (!game.isPaused()) {
            game.draw(g2D);
            ui.drawScore(g2D);
        } else {
            ui.drawGameOverMenu(g2D);
        }
    }

    private Color randomBallColor() {
        return switch ((int)(Math.random() * 3)) {
            case 0 -> Color.RED;
            case 1 -> Color.GREEN;
            default -> Color.BLUE;
        };
    }

    
}

import javax.swing.*;
import java.awt.event.*;

public class MyFrame extends JFrame implements KeyListener {
    private GameLogic game = GameLogic.getInstance();
    private UserInterface ui = new UserInterface();
    private MyPanel panel;

    public MyFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new MyPanel();

        this.addKeyListener(this);
        this.setFocusable(true);
        this.requestFocusInWindow();

        this.add(panel);
        this.pack();
        this.setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        game.handleKeyPress(e.getKeyChar());
        ui.handleKeyPress(e.getKeyChar());
        panel.repaint();
    }
}

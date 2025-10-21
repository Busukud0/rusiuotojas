import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MyFrame extends JFrame implements KeyListener {
    private GameLogic gameLogic = GameLogic.getInstance();
    private MyPanel panel;
    MyFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new MyPanel();

        this.addKeyListener(this);
        this.setFocusable(true);
        this.requestFocusInWindow();

        this.add(panel);
        this.pack();
        this.setVisible(true);
    }

    // key listener methods
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        gameLogic.handleKeyPress(e.getKeyChar());
        panel.repaint();
    }
}
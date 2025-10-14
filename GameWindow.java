import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameWindow {
    private JFrame frame;
    private JButton btn;
    private GameLogic gameLogic;

    public GameWindow() {
        frame = new JFrame("Rūšiuotojas");
        btn = new JButton("Start");
        gameLogic = new GameLogic();
    }

    public void show() {
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setBounds(150, 150, 250, 150);
        frame.setVisible(true);
        frame.add(btn);
        btn.setBounds(50, 50, 100, 30);

        btn.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                gameLogic.handleKeyPress(e.getKeyChar());
            }
        });
    }
}

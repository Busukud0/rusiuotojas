import javax.swing.JPanel;
import java.awt.*;

public class MyPanel extends JPanel {
	private int boxSize = 100,
				gap = 50,
				scrWidth = 500,
				scrHeight = 500,
				lineOffset = 15;
	MyPanel(){
		this.setPreferredSize(new Dimension(scrWidth, scrHeight));
	}

	
    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;

        g2D.setStroke(new BasicStroke(3));

        g2D.drawLine(scrWidth/2-lineOffset,50,250-lineOffset,200);
        g2D.drawLine(scrWidth/2+lineOffset,50,250+lineOffset,200);

        g2D.drawLine(scrWidth/2-lineOffset,200,50 + boxSize/2-lineOffset,350);
        g2D.drawLine(scrWidth/2-lineOffset,200+lineOffset*2,50 + boxSize/2+lineOffset,350);
        
        g2D.drawLine(scrWidth/2-lineOffset,200+lineOffset*2,gap+boxSize+gap+boxSize/2-lineOffset,350);
        g2D.drawLine(scrWidth/2+lineOffset,200+lineOffset*2,gap+boxSize+gap+boxSize/2+lineOffset,350);
        
        g2D.drawLine(scrWidth/2+lineOffset,200+lineOffset*2,gap+boxSize+gap+boxSize+gap + boxSize/2-lineOffset,350);
		g2D.drawLine(scrWidth/2+lineOffset,200,gap+boxSize+gap+boxSize+gap + boxSize/2+lineOffset,350);

        g2D.setPaint(Color.red);
        g2D.fillRect(gap, 350, boxSize, boxSize);

        g2D.setPaint(Color.green);
        g2D.fillRect(gap+boxSize+gap, 350, boxSize, boxSize);

        g2D.setPaint(Color.blue);
        g2D.fillRect(gap+boxSize+gap+boxSize+gap, 350, boxSize, boxSize);
    }
}
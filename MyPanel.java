import javax.swing.JPanel;
import java.awt.*;

public class MyPanel extends JPanel {
	private int boxSize = 100,
				scrWidth = 500,
				scrHeight = 500,
                topY = 50,
                bottomY = 350,
                
                lineOffset = boxSize/5,
                gap = (scrWidth-boxSize*3)/4,
                leftX = gap + boxSize/2,
                middleX = gap + boxSize + gap + boxSize/2,
                rightX = gap + boxSize + gap + boxSize + gap + boxSize/2;
	MyPanel(){
		this.setPreferredSize(new Dimension(scrWidth, scrHeight));
	}
    GameLogic logic = GameLogic.getInstance();
	
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // clears previous lines/colors

        Graphics2D g2D = (Graphics2D) g;
        g2D.setStroke(new BasicStroke(3));

        // draw lever-dependent lines
        switch(logic.getLever()) {
            case 0 -> {
                g2D.setPaint(Color.red);
                g2D.drawLine(scrWidth/2+lineOffset,200,scrWidth/2-lineOffset,200+lineOffset*2);
            }
            case 1 -> {
                g2D.setPaint(Color.green);
                g2D.drawLine(scrWidth/2-lineOffset,200,scrWidth/2-lineOffset,200+lineOffset*2);
                g2D.drawLine(scrWidth/2+lineOffset,200,scrWidth/2+lineOffset,200+lineOffset*2);
            }
            case 2 -> {
                g2D.setPaint(Color.blue);
                g2D.drawLine(scrWidth/2-lineOffset,200,scrWidth/2+lineOffset,200+lineOffset*2);
            }
        }
        g2D.setPaint(Color.black);
        // upper middle lines
        g2D.drawLine(scrWidth/2-lineOffset,topY,scrWidth/2-lineOffset,200);
        g2D.drawLine(scrWidth/2+lineOffset,topY,scrWidth/2+lineOffset,200);
        // left lines
        g2D.drawLine(scrWidth/2-lineOffset,200,gap+boxSize/2-lineOffset,350);
        g2D.drawLine(scrWidth/2-lineOffset,200+lineOffset*2,leftX+lineOffset,350);
        // lower middle lines
        g2D.drawLine(scrWidth/2-lineOffset,200+lineOffset*2,middleX-lineOffset,350);
        g2D.drawLine(scrWidth/2+lineOffset,200+lineOffset*2,middleX+lineOffset,350);
        // right lines
        g2D.drawLine(scrWidth/2+lineOffset,200+lineOffset*2,rightX-lineOffset,350);
        g2D.drawLine(scrWidth/2+lineOffset,200,rightX+lineOffset,350);

        // draw boxes
        g2D.setPaint(Color.red);
        g2D.fillRect(gap, 350, boxSize, boxSize);

        g2D.setPaint(Color.green);
        g2D.fillRect(gap+boxSize+gap, 350, boxSize, boxSize);

        g2D.setPaint(Color.blue);
        g2D.fillRect(gap+boxSize+gap+boxSize+gap, 350, boxSize, boxSize);
    }
}
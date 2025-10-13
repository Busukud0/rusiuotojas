/*
Programų sistemų projektavimas (su kursiniu darbu) (FMF)

1. Rūšiuotojas

Žaidėjas valdo konvejerio sklendes ir turi nukreipti įvairių tipų dėžes	į teisingus konteinerius.
Reikalavimai:
• - Konvejeris nuolat generuoja	atsitiktinio tipo dėžes	(pvz. A, B, C).
• - Žaidėjas klavišais (1, 2, 3) perjungia sklendes.
• - Dėžė patenka į konteinerį pagal sklendės padėtį.
• - Teisingas nukreipimas prideda taškų, klaida	atima gyvybę.
• - Žaidimas baigiasi, kai baigiasi gyvybės arba pasiekiamas taškų limitas.
• - Pabaigoje rodoma rezultatų santrauka.
Papildiniai: Kombo daugikliai, „klaidų	serijos“ baudos, lygių progresija
*/

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton; //for the button
import javax.swing.JFrame; //for the UI window

public class main
{
	private JFrame frame;
	private JButton btn;

	public main() {
		frame = new JFrame("Rūšiuotojas");
		btn = new JButton("Start");
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
				System.out.println("you pressed: " + e.getKeyChar());
				// KEY PRESSED!
			}
		});
	}

	//MAIN
	public static void main(String[] args) 
    {
    	System.out.println(getRandNum(3));
    	main t=new main();
    	t.show();
    }








    //random number generator
	public static int getRandNum(int count) 
	{
		return (int)(Math.random() * count);
	}

}

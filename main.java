/*
Programų sistemų projektavimas (su kursiniu darbu) (FMF)
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

public class Main {
    public static void main(String[] args) {
        GameWindow gameWindow = new GameWindow();
        gameWindow.show();
    }
}
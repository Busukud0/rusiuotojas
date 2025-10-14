public class GameLogic {
    private int score = 0;
    private int lives = 3;

    public void handleKeyPress(char key) {
        switch (key) {
            case '1' -> System.out.println("Sklende 1 pasirinkta");
            case '2' -> System.out.println("Sklende 2 pasirinkta");
            case '3' -> System.out.println("Sklende 3 pasirinkta");
            default -> System.out.println("Neteisingas klavisas");
        }
    }

    public static char getRandomBoxType(int count) {
        return (char) ('A' + Math.random() * count);
    }
}

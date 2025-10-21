public class GameLogic {
    private static final GameLogic instance = new GameLogic();
    private int score = 0;
    private int lives = 3;
    private int lever = 1;

    private GameLogic() {}

    public static GameLogic getInstance() {
        return instance;
    }

    public void handleKeyPress(char key) {
        switch (key) {
            case '1' -> {
                this.lever = 0;
                System.out.println("1 sklende pasirinkta");
            }
            case '2' -> {
                this.lever = 1;
                System.out.println("2 sklende pasirinkta");
            }
            case '3' -> {
                this.lever = 2;
                System.out.println("3 sklende pasirinkta");
            }
            default -> System.out.println("Neteisingas klavisas");
        }
    }

    public static char getRandomBoxType(int count) {
        return (char) ('A' + Math.random() * count);
    }

    public int getLever() {
        return lever;
    }
}

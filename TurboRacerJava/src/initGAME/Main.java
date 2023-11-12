package initGAME;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        GameController gameController = new GameController();
        try {
            gameController.startGame();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
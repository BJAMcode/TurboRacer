package initGAME;

import gridFactory.Grid;

import java.io.IOException;

public class GameController {
    public static int restartCounter = 0;
    public static Game game = new Game();

    public void startGame() throws IOException {
        game.start();
    }

    public static void restartGame() throws IOException {
        game.start();
    }
}

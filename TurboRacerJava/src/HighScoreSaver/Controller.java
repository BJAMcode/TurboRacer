package HighScoreSaver;

import initGAME.Game;

import java.io.IOException;

public class Controller {

    public static void refreshScore() throws IOException {
        String file = Reader.read();
        int fileScore;
        int currentScore = Game.score;
        if (file == null) {
            Writer.write(String.valueOf(currentScore));
            Game.highScore = currentScore;
        } else {
            fileScore = Integer.valueOf(file);
            if (currentScore > fileScore) {
                Game.highScore = currentScore;
                Writer.write(String.valueOf(currentScore));
            } else {
                Game.highScore = fileScore;
            }
        }
    }


    /*public static void updateScores() throws IOException {
        String oldFile = Reader.read();
        String newScore = String.valueOf(Game.score);
        String newFile;
        if (oldFile != null) {
            newFile = oldFile + newScore;
            Writer.write(newFile + " ");
        } else {
            newFile = newScore;
            Writer.write(newFile+" ");
        }
    }*/
}

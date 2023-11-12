package gridFactory;

import initGAME.Game;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.pictures.Picture;


public class Grid {
    public static final int PADDINGY = 120;
    public static final int PADDINGX = 220;
    public static final int cols = 810;
    public static final int rows = 600;
    private static Picture roadImage;
    private static int whichRoad = 0;

    private static Picture gameOver = new Picture(10, 0, "images/gameoverfinal.png");

    private static Text finalScore;
    private static Text highScore;


    public static void initgrid() {
        roadImage = new Picture(10, 0, "images/linha1.png");
        roadImage.draw();
    }

    public static void alternateImages() {
        switch (whichRoad) {
            case 0:
                roadImage.load("images/linha1.png");
                whichRoad++;
                break;
            case 1:
                roadImage.load("images/linha2.png");
                whichRoad++;
            case 2:
                roadImage.load("images/linha3.png");
                whichRoad = 0;
                break;
        }
    }


    public static void gameOver() {
        gameOver.draw();
        finalScore = new Text(1010, 623, "YOUR SCORE: " + Game.score);
        finalScore.setColor(Color.GREEN);
        finalScore.grow(55, 30);
        finalScore.draw();
        highScore = new Text(1010, 663, "HIGHSCORE  : " + Game.highScore);
        highScore.setColor(Color.GREEN);
        highScore.grow(55, 30);
        highScore.draw();
    }

    public static void deleteGameOverScreen() {
        gameOver.delete();
        finalScore.delete();
        System.out.println(" deleteMenu");
    }

    public static void deleteRoad(Picture picture) {
        picture.delete();
    }

    public static int getCols() {
        return cols;
    }

    public static int getRows() {
        return rows;
    }

    public static void moveBackground() {
        roadImage.translate(0, 0);
    }

    public static Picture getRoadImage() {
        return roadImage;
    }

    public static void setRoadImage(Picture roadImage) {
        Grid.roadImage = roadImage;
    }
}

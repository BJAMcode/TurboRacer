package carFactory;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class YellowCar extends Car {
    private static int yellowSpeed = 3;

    public YellowCar(int startPosition) {
        super(CarType.YELLOW, new Picture(startPosition, -30, "images/caryellow.png"), yellowSpeed);
    }
    public static void increaseYellowSpeed(int speed){
        yellowSpeed+=speed;
    }
}

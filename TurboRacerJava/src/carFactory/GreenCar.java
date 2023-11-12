package carFactory;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class GreenCar extends Car{
    private static int greenSpeed=3;
    public GreenCar(int startPosition) {
        super(CarType.GREEN,new Picture(startPosition,-30,"images/cargreen.png"),greenSpeed);
    }
    public static void increaseGreenSpeed(int speed){
        greenSpeed+=speed;
    }

    public static int getGreenSpeed() {
        return greenSpeed;
    }
}

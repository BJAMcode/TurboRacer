package carFactory;

import gridFactory.Grid;
import initGAME.Game;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.List;

public class Car {
    private final Picture picture;
    private final CarType carType;
    private int carsSpeed;

    public Car(CarType carType, Picture picture, int speed) {
        this.carType = carType;
        this.picture = picture;
        this.carsSpeed = speed;
        this.picture.draw();
    }
    public void moveCar(List cars, Car car) {
        car.getPicture().translate(0, car.carsSpeed);
        if (car.getPicture().getMaxY() > Grid.PADDINGY + Grid.rows - 30) {
            car.getPicture().delete();
            cars.remove(car);
            Game.score += 1;
            Game.updateScore();
        }
    }
    public Picture getPicture() {
        return picture;
    }
    public int getCarSpeed() {
        return carsSpeed;
    }
    public CarType getCarType() {
        return carType;
    }
    public void setCarSpeed(int carsSpeed) {
        this.carsSpeed = carsSpeed;
    }
}

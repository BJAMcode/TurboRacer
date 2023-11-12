package carFactory;

public class CarFactory {
    private int firstLane = 302;
    private int secondLane = 482;
    private int thirdLane = 680;
    private int fourthLane = 860;
    private int leftLimit = 216;
    private int between1And2 = 395;
    private int middle = 584;
    private int between3And4 = 764;
    private int rightLimit = 954;
    private static int lastStartPosition = 0;

    public Car getNewCar() {
        int randomCar = (int) Math.floor(Math.random() * 4);
        Car car = switch (randomCar) {
            case 0 -> new GreenCar(carStartPosition());
            case 1 -> new YellowCar(carStartPosition());
            case 2 -> new Scooter(scooterStartPosition());
            default -> new GreenCar(carStartPosition());
        };
        return car;
    }

    // Decide em que lane o carro dá spawn.
    // O valor que retorna é usado para decidir o x inicial dos nossos carros
    // nos respetivos construtores.
    // O lastStartPosition guarda a lane em que o último carro deu spawn e isso
    // mais o while impedem que dois carros seguidos spawnem na mesma lane
    public int carStartPosition() {
        int randomLane = lastStartPosition;
        while (randomLane == lastStartPosition) {
            randomLane = (int) Math.floor(Math.random() * 4);
        }
        int startPosition = switch (randomLane) {
            case 0 -> firstLane;
            case 1 -> secondLane;
            case 2 -> thirdLane;
            case 3 -> fourthLane;
            default -> 0;
        };
        lastStartPosition = randomLane;
        return startPosition;
    }

    public int scooterStartPosition() {
        int randomLane = lastStartPosition;
        while (randomLane == lastStartPosition) {
            randomLane = (int) Math.floor(Math.random() * 5);
        }
        int startPosition = switch (randomLane) {
            case 0 -> leftLimit;
            case 1 -> between1And2;
            case 2 -> middle;
            case 3 -> between3And4;
            case 4 -> rightLimit;
            default -> 0;
        };
        lastStartPosition = randomLane;
        return startPosition;
    }

    public static String chooseScooterPicture() {
        int randomImage = (int) Math.floor(Math.random() * 4);
        String scooterImage = switch (randomImage) {
            case 0 -> "images/mota.png";
            case 1 -> "images/mota2.png";
            case 2 -> "images/mota3.png";
            case 3 -> "images/mota4.png";
            default -> "";
        };
        return scooterImage;
    }
}

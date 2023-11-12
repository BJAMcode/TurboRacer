package initGAME;

import HighScoreSaver.Controller;
import carFactory.*;
import gridFactory.Grid;
import music.Music;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static gridFactory.Grid.gameOver;

public class Game {
    PlayerCar playerCar;
    public static int highScore;
    CarFactory carFactory = new CarFactory();
    private static List<Car> cars = new LinkedList<>();
    private static List<Elements> elements = new LinkedList<>();
    private static List<Picture> stars = new LinkedList<>();
    private int carSpawnTimer = 100;
    private int carSpawnTimerCounter = 0;
    private int imageAlternateCounter = 0;
    private int imageAlternateTimer = 20;
    private int gameSpeed = 0;
    public static int score = 0;
    public static int level = 0;
    public static boolean gameOver = false;
    public static boolean gameStarted = false;

    private int coinSpawnTimerCounter = 0;
    private int drunkTimer = 0;
    private Picture contador;
    private Picture wantedText;
    public static Text textScore;
    private int starX = 1075;
    private int starY = 80;
    Music initialMusic = new Music("Musics/testesom.wav");
    Music duringGame = new Music("Musics/duringGameFinal.wav");
    Music policeSong = new Music("Musics/police.wav");
    Music voiceOne = new Music("Musics/voz1.wav");
    Music voiceTwo = new Music("Musics/voz2.wav");
    private Picture threeLifes = new Picture(1073, 10, "images/vida3.png");
    private Picture twoLifes = new Picture(1073, 10, "images/vida2.png");
    private static Picture oneLife = new Picture(1073, 10, "images/vida1.png");

    public Game() {
        playerCar = new PlayerCar();
    }

    public void start() throws IOException {
        initialMusic.play();
        if (GameController.restartCounter == 0) {
            Grid.initgrid();
        }

        MenuStart.MenuStart();

        if (GameController.restartCounter == 0) {
            playerCar.init();
        }
        while (!playerCar.isEnterKeyPressed()) {
            CustomSleep.sleep(10);
        }
        gameStarted = true;
        cars.add(carFactory.getNewCar());
        initialMusic.stop();
        play();
    }

    public void play() throws IOException {
        initializeScoreAndLevel();
        duringGame.play();
        policeSong.play();

        while (!gameOver) {
            CustomSleep.sleep(15);
            spawnNewCar();
            spawnNewCoin();
            gameSpeed++;

            if (playerCar.getHp() > 2) {
                threeLifes.draw();
            } else if (playerCar.getHp() == 2) {
                voiceOne.play();
                threeLifes.delete();
                twoLifes.draw();
            } else {
                voiceTwo.play();
                twoLifes.delete();
                oneLife.draw();
            }
            if (gameSpeed == 300) {
                if (PlayerCar.getPlayerSpeed() < 9) {
                    PlayerCar.increasePlayerSpeed(PlayerCar.getPlayerSpeed() + 1);
                }
                Game.level++;
                System.out.println(Game.level);
                if (Game.level < 9) {
                    updateLevel();
                    imageAlternateTimer--;
                }
            }
            for (int i = 0; i < cars.size(); i++) {
                cars.get(i).moveCar(cars, cars.get(i));
                if (gameSpeed == 300) {
                    if (Scooter.getScooterSpeed() < 13) {
                        cars.get(i).setCarSpeed(cars.get(i).getCarSpeed() + 2);
                        Scooter.increaseScooterSpeed(1);
                        YellowCar.increaseYellowSpeed(1);
                        GreenCar.increaseGreenSpeed(1);
                    }
                    gameSpeed = 0;
                }
            }
            if (playerCar.isSwitchControls()) {
                drunkTimer++;
                System.out.println(drunkTimer);
                if (drunkTimer >= 300) {
                    playerCar.setNormalControls();
                    drunkTimer = 0;
                }
            }
            movePlayer();
            alternateImages();
            detectCollision();
            pickCoin();
        }
    }

    public void movePlayer() {
        if (playerCar.isRightPressed()) {
            playerCar.moveRight();
        }
        if (playerCar.isLeftPressed()) {
            playerCar.moveLeft();
        }
        if (playerCar.isUpPressed()) {
            playerCar.moveUp();
        }
        if (playerCar.isDownPressed()) {
            playerCar.moveDown();
        }
    }

    public void spawnNewCar() {
        carSpawnTimerCounter++;
        if (carSpawnTimerCounter == carSpawnTimer) {
            carSpawnTimerCounter = 0;
            cars.add(carFactory.getNewCar());
            if (carSpawnTimer > 15) {
                carSpawnTimer -= 4;
            }
            if (Scooter.getScooterSpeed() < 13) {
                if (carSpawnTimer < 25) {
                    carSpawnTimer = 25;
                }
            } else {
                carSpawnTimer = 15;
            }
        }
    }

    public void spawnNewCoin() {
        coinSpawnTimerCounter++;
        if (coinSpawnTimerCounter == 200) {
            coinSpawnTimerCounter = 0;
            elements.add(Elements.getNewElement());
        }
    }

    public void initializeScoreAndLevel() {
        contador = new Picture(0, 0, "images/contador1.png");
        contador.draw();
        wantedText = new Picture(1070, 40, "images/wantedText.png");
        wantedText.draw();
        textScore = new Text(598, 7, "YOUR SCORE: " + Game.score);
        textScore.draw();
        textScore.setColor(Color.GREEN);
        textScore.grow(25, 10);
    }

    public static void updateScore() {
        if (textScore != null) {
            textScore.delete();
        }
        textScore = new Text(598, 7, "YOUR SCORE: " + Game.score);
        textScore.draw();
        textScore.setColor(Color.GREEN);
        textScore.grow(25, 10);
        textScore.draw();
    }

    public void updateLevel() {
        if (Game.level <= 5) {
            Picture newStar = new Picture(starX, starY, "images/star.png");
            newStar.draw();
            starX += 18;
            stars.add(newStar);
        } else {
            if (starY == 80) {
                starX = 1057;
                starY += 18;
            }
            starX += 18;
            Picture newStar = new Picture(starX, starY, "images/star.png");
            newStar.draw();
        }
    }

    public void alternateImages() {
        imageAlternateCounter++;
        if (imageAlternateCounter >= imageAlternateTimer) {
            Grid.alternateImages();
            imageAlternateCounter = 0;
        }
    }

    public void detectCollision() throws IOException {
        for (int i = 0; i < cars.size(); i++) {
            if (playerCar.isColliding(cars.get(i).getPicture())) {
                cars.get(i).getPicture().delete();
                cars.remove(cars.get(i));
                if (playerCar.getHp() == 0) {
                    Game.textScore.delete();
                    Controller.refreshScore();
                    duringGame.stop();
                    policeSong.stop();
                    gameOver = true;
                    gameOver();
                }
            }
        }
    }

    public void pickCoin() {
        for (int i = 0; i < elements.size(); i++) {
            if (playerCar.pickCoin(elements.get(i).getCoin())) {
                elements.get(i).getCoin().delete();
                elements.remove(elements.get(i));
                int rnd = (int) Math.floor(Math.random() * 4);
                if (rnd != 2) {
                    Music coinSound = new Music("Musics/coin.wav");
                    coinSound.play();
                    updateScore();
                } else {
                    Music drunk = new Music("Musics/openBottle.wav");
                    drunk.play();
                    playerCar.setSwitchControls();
                    drunkTimer = 0;
                }
            }
        }
    }

    public static void deleteElements() {
        oneLife.delete();
        for (Picture star : stars) {
            star.delete();
        }
        stars.clear();
        for (Car car : cars) {
            car.getPicture().delete();
        }
        cars.clear();
        for (Elements element : elements) {
            element.getCoin().delete();
            System.out.println("passou");
        }
        elements.clear();
    }
}

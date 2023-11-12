package carFactory;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Elements {
    public Picture coin;

    public Elements() {
        this.coin = new Picture(spawnY(), spawnX(), "images/moeda.png");
        coin.draw();
    }
    public Picture getCoin() {
        return coin;
    }

    public static Elements getNewElement() {
        int randomElement = (int) Math.floor(Math.random() * 2);
        if (randomElement == 0) {
            return new Elements();
        } else {
            return new Elements();
        }
    }
    public int spawnY() {
        int randomY = (int) Math.floor(Math.random() * (739) + 220);
        return randomY;
    }
    public int spawnX() {
        int randomX = (int) Math.floor(Math.random() * (550) + 120);
        return randomX;
    }
}

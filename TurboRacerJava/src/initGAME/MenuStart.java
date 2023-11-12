package initGAME;

import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.Scanner;

public class MenuStart {

    private static Picture picture;


    public static void MenuStart(){
        picture = new Picture(10,0,"images/menufinal.png");
        picture.draw();
    }




    public static void DeleteMenu(){
        picture.delete();
    }

}

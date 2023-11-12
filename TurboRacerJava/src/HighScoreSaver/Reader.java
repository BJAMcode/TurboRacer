package HighScoreSaver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Reader {
    public static String read() throws IOException {
        FileReader reader = new FileReader("resources/HighScoreList.txt");
        BufferedReader breader = new BufferedReader(reader);
        String line = breader.readLine();
        String result;
        if ((line) != null) {
            result = line;
            System.out.println("linha nao nula");
        } else {
            breader.close();
            return null;
        }
        breader.close();
        return result;
    }
}

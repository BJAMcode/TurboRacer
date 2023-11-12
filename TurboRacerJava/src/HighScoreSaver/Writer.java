package HighScoreSaver;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Writer {

    public static void write(String score) throws IOException {
        FileWriter writer = new FileWriter("resources/HighScoreList.txt");
        BufferedWriter bwriter = new BufferedWriter(writer);
        bwriter.write(score);
        bwriter.close();
    }
}

package music;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Music {
    private Clip clip;



    public Music(String filePath) {


        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
           // clip.loop(Clip.LOOP_CONTINUOUSLY);
            // NÃO PODE FICAR ATIVO SENÃO FODE O
            // SOM DO COLISION E FICA SEMPRE A REPETIR.
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
    }


    public void play() {
        if (clip != null) {
            clip.start();
        }
    }

    public void restart(){
        clip.start();
    }

    public void stop() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }





}

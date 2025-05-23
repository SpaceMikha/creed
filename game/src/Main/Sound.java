package main;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
    
    Clip clip;
    URL soundURL[] = new URL[30]; // Array to hold sound URLs

    public Sound() {

        soundURL[0] = getClass().getResource("/res/sound/BlueBoyAdventure.wav");
        soundURL[1] = getClass().getResource("/res/sound/coin.wav");
        soundURL[2] = getClass().getResource("/res/sound/powerup.wav");
        soundURL[3] = getClass().getResource("/res/sound/unlock.wav");
        soundURL[4] = getClass().getResource("/res/sound/fanfare.wav");


    }

    public void setFile(int i){
    
        // Set the file to be played based on the index
            try {
                AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
                clip = AudioSystem.getClip();
                clip.open(ais);

            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    public void play(){

        clip.start(); // Start playing the sound

    }

    public void loop(){

        clip.loop(Clip.LOOP_CONTINUOUSLY); // Loop the sound continuously

    }

    public void stop(){

        clip.stop(); // Stop playing the sound

    }
}

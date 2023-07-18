package com.khomsi.game.main.tools;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;


public class Sound {
    public Clip clip;
    private final String resourcePath = "/sounds/";
    FloatControl fc;
    public int volumeScale = 3;
    float volume;
    URL[] soundURL = new URL[30];

    public Sound() {
        //TODO make it automatic in future
        soundURL[0] = getResource("main-theme.wav");
        soundURL[1] = getResource("snd_pombark.wav");
        soundURL[2] = getResource("mus_sfx_a_grab.wav");
        soundURL[3] = getResource("mus_dununnn.wav");
        soundURL[4] = getResource("mus_dooropen.wav");
        soundURL[5] = getResource("mus_doorclose.wav");
        soundURL[6] = getResource("snd_power.wav");
        soundURL[7] = getResource("snd_victory.wav");
        soundURL[8] = getResource("hitmonster.wav");
        soundURL[9] = getResource("receivedamage.wav");
        soundURL[10] = getResource("sword-swipes.wav");
        soundURL[11] = getResource("level_up.wav");
        soundURL[12] = getResource("cursor.wav");
        soundURL[13] = getResource("cuttree.wav");
        soundURL[14] = getResource("game_over.wav");
        soundURL[15] = getResource("sleep.wav");
        soundURL[16] = getResource("parry.wav");
        soundURL[17] = getResource("blocked.wav");
        soundURL[18] = getResource("snd_text_2.wav");
        soundURL[19] = getResource("Merchant.wav");
        soundURL[20] = getResource("Dungeon.wav");
        soundURL[21] = getResource("snd_switchpull_n.wav");
        soundURL[22] = getResource("boss_music.wav");
        soundURL[23] = getResource("fight-theme.wav");
    }

    private URL getResource(String path) {
        return getClass().getResource(resourcePath + path);
    }

    public void setFile(int i) {
        try {
            AudioInputStream stream = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(stream);

            // Check if the control is supported before retrieving it
            if (clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
                fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                checkVolume();
            } else {
                System.err.println("Master Gain control is not supported.");
                // Play the sound without applying volume adjustment
                clip.start(); // Start playing the sound
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.err.println("Error in method setFile in class " + getClass().getSimpleName());
            e.printStackTrace();
        }
    }


    public void play() {
        clip.start();
    }


    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }

    public void checkVolume() {
        switch (volumeScale) {
            case 0 -> volume = -80F;
            case 1 -> volume = -20F;
            case 2 -> volume = -12F;
            case 3 -> volume = -5F;
            case 4 -> volume = 1F;
            case 5 -> volume = 6F;
        }
        fc.setValue(volume);
    }
}

package com.sheep.game.util;

import javax.sound.sampled.*;
import java.io.IOException;

public class AudioPlayer {
    public static String SFX_HOVER = "/sound/hover.wav";
    public static String SFX_CLICK = "/sound/click.wav";

    public static String WOOD_CHOP = "/sound/wood_chop.wav";
    public static String STONE_HIT = "/sound/stone_hit.wav";

    private Clip clip;

    public void loadSound(String path) {
        try
        {
            AudioInputStream stream = AudioSystem.getAudioInputStream(AudioPlayer.class.getResource(path));
            clip = AudioSystem.getClip();
            clip.open(stream);
        }
        catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }

    public void play(){
        clip.start();
    }

    public void stop(){
        clip.stop();
    }

    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
}

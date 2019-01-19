package com.ovgu.ccd.applogic;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * This helps in playing audio files.
 */

final public class AudioPlayer {

    /**
     * Stores the current position.
     */
    private Clip mClip;

    /**
     * This is the default constructor of the class.
     * This fetches the audio file from the location
     * and set it for playing.
     */
    public AudioPlayer() {
        // TODO Auto-generated constructor stub
        try {
            String mFileName = "success.wav";

            ClassLoader mClassLoader = getClass().getClassLoader();

            InputStream mInputFile = new BufferedInputStream(
                    mClassLoader.getResourceAsStream(mFileName));

            // create AudioInputStream object
            AudioInputStream mAudioInputStream = AudioSystem
                    .getAudioInputStream(mInputFile);

            // create clip reference
            mClip = AudioSystem.getClip();

            // open audioInputStream to the clip
            mClip.open(mAudioInputStream);

            mClip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException
                | LineUnavailableException
                | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * This function helps to start to play the
     * audio file.
     */
    public void play() {
        // TODO Auto-generated method stub
        // start the clip
        mClip.start();
    }

    /**
     * This function helps to stop the playing
     * audio file after a certain time.
     * @param ms is the time in milliseconds.
     */
    public void stop(final int ms) {
        // TODO Auto-generated method stub
        while (true) {
            try {
                Thread.sleep(ms);
                break;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        mClip.stop();
        mClip.close();
    }
}

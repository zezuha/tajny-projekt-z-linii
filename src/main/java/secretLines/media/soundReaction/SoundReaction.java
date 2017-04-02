package secretLines.media.soundReaction;

import processing.core.PApplet;
import processing.sound.AudioDevice;
import processing.sound.FFT;
import processing.sound.SoundFile;
import secretLines.config.ConfigConstants;

public class SoundReaction {

    private PApplet parent;
    private AudioDevice device;
    private SoundFile soundSource;
    private FFT fft;
    private int bandsCount;
    private float[] bandValues;
    private float smoothFactor;

    public SoundReaction(PApplet parent, int bandsCount) {
        this.parent = parent;
        this.bandsCount = bandsCount;
        device = new AudioDevice(parent, 44000, bandsCount);
        soundSource = new SoundFile(parent, ConfigConstants.MUSIC_PATH);
        soundSource.loop();
        fft = new FFT(parent, bandsCount);
        fft.input(soundSource);
        bandValues = new float[bandsCount];
        for (int i = 0; i < bandsCount; i++) {
            bandValues[i] = 0;
        }
        smoothFactor = 0.2f;
    }

    public SoundReaction(PApplet parent) {
        this(parent, 128);
    }

    public void update() {
        fft.analyze();
        for (int i = 0; i < bandsCount; i++) {
            bandValues[i] += (fft.spectrum[i] - bandValues[i]) * smoothFactor;
        }
    }

    public int getBandsCount() {
        return bandsCount;
    }

    public float getBandValue(int bandIndex) {
        return bandValues[bandIndex] * 2;
    }
}

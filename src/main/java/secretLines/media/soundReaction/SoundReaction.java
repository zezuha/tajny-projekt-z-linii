package secretLines.media.soundReaction;

import processing.core.PApplet;
import processing.sound.AudioDevice;
import processing.sound.FFT;
import processing.sound.SoundFile;
import secretLines.config.ConfigConstants;
import secretLines.midi.MidiReaction;

import static java.lang.Math.log10;
import static processing.core.PApplet.map;

public class SoundReaction implements MidiReaction {

    private PApplet parent;
    private AudioDevice device;
//    private AudioIn soundSource;
    private SoundFile soundSource;
    private FFT fft;
    private int bandsCount;
    private float[] bandValues;
    private float[] bandValuesLog10;
    private float smoothFactor;
    private float levelReduction;

    public SoundReaction(PApplet parent, int bandsCount) {
        this.parent = parent;
        this.bandsCount = bandsCount;
        device = new AudioDevice(parent, 44000, bandsCount);
//        soundSource = new AudioIn(parent, 0);//new SoundFile(parent, ConfigConstants.MUSIC_PATH);
        soundSource = new SoundFile(parent, ConfigConstants.MUSIC_PATH);
        soundSource.play();
//        soundSource.loop();
        fft = new FFT(parent, bandsCount);
        fft.input(soundSource);
        bandValues = new float[bandsCount];
        bandValuesLog10 = new float[bandsCount];
        for (int i = 0; i < bandsCount; i++) {
            bandValues[i] = 0;
            bandValuesLog10[i] = 0;
        }
        smoothFactor = 0.2f;
        levelReduction = 1;
    }

    public SoundReaction(PApplet parent) {
        this(parent, 128);
    }

    public void update() {
        fft.analyze();
        for (int i = 0; i < bandsCount; i++) {
            bandValues[i] += (fft.spectrum[i] - bandValues[i]) * smoothFactor;
            bandValuesLog10[i] = 1 + ((float)log10(bandValues[i])/levelReduction);
        }
    }

    public int getBandsCount() {
        return bandsCount;
    }

    public float getBandValueLog10(int bandIndex) {
        return bandValuesLog10[bandIndex];
    }

    public boolean isBandAbove(int bandIndex, float treshold) {
        return getBandValueLog10(bandIndex) > treshold;
    }

    public void controllerChange(int channel, int number, int value) {
        switch (number) {
            case 7: { //most right slider
                levelReduction = map(value, 0, 127, 3, 10);
            }
            break;
            case 23: { //most right knob
                smoothFactor = map(value, 0, 127, 0.01f, 1);
            }
            break;
        }
    }

    public void noteOn(int channel, int pitch, int velocity) {

    }
    public void noteOff(int channel, int pitch, int velocity) {

    }
}

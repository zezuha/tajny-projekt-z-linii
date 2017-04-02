package secretLines.midi;

public interface MidiReaction {
    void controllerChange(int channel, int number, int value);
    void noteOn(int channel, int pitch, int velocity);
    void noteOff(int channel, int pitch, int velocity);
}

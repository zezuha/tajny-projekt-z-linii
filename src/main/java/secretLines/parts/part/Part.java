package secretLines.parts.part;

import processing.core.PApplet;
import secretLines.midi.MidiReaction;

public abstract class Part implements MidiReaction {

    protected PApplet parent;

    protected boolean isActive;

    protected Part() {
        isActive = false;
    }

    protected Part(PApplet parent) {
        this();
        this.parent = parent;
    }

    public boolean isActive() {
        return isActive;
    }

    public void start() {
        isActive = true;
    }

    public void finish() {
        isActive = false;
    }

    public abstract void draw();

    public void controllerChange(int channel, int number, int value) {}

    public void noteOn(int channel, int pitch, int velocity) {}

    public void noteOff(int channel, int pitch, int velocity) {}
}

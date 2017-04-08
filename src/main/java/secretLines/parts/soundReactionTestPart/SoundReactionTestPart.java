package secretLines.parts.soundReactionTestPart;


import processing.core.PApplet;
import processing.core.PImage;
import secretLines.config.ConfigConstants;
import secretLines.media.soundReaction.SoundReaction;
import secretLines.midi.MidiReaction;
import secretLines.parts.part.Part;

import static processing.core.PApplet.map;

public class SoundReactionTestPart extends Part implements MidiReaction {

    private SoundReaction soundReaction;
    private int alpha;

    public SoundReactionTestPart(PApplet parent, SoundReaction soundReaction) {
        super(parent);
        this.soundReaction = soundReaction;
        alpha = 255;
    }

    public void draw() {
//        parent.background(soundReaction.getBandValue(12) * 255);
        float bandWidth = parent.width / soundReaction.getBandsCount();
        parent.stroke(alpha, alpha);
        parent.fill(alpha, alpha);
        for (int i = 0; i < soundReaction.getBandsCount(); i++) {
            parent.strokeWeight(soundReaction.getBandValue(i) * 10);
            parent.line(i * bandWidth, 0, i * bandWidth + bandWidth/2, soundReaction.getBandValue(i) * parent.height);
            parent.line((i + 1) * bandWidth, 0, (i + 1) * bandWidth - bandWidth/2, soundReaction.getBandValue(i) * parent.height);
            parent.text(i, i * bandWidth, 10);
            parent.text(soundReaction.getBandValue(i), i * bandWidth, 20);
        }
    }

    public void controllerChange(int channel, int number, int value) {
        switch (number) {
            case 6: {
                alpha = (int)map(value, 0, 127, 0, 255);
            }
            break;
        }
    }

    public void noteOn(int channel, int pitch, int velocity) {

    }
    public void noteOff(int channel, int pitch, int velocity) {

    }
}

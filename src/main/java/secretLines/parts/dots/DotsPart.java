package secretLines.parts.dots;

import processing.core.PApplet;
import secretLines.media.soundReaction.SoundReaction;
import secretLines.midi.MidiReaction;
import secretLines.parts.part.Part;

import static processing.core.PApplet.map;

public class DotsPart extends Part implements MidiReaction {

    private SoundReaction soundReaction;
    private int alpha1;
    private int alpha2;

    public DotsPart(PApplet parent, SoundReaction soundReaction) {
        super(parent);
        this.soundReaction = soundReaction;
        alpha1 = 0;
    }



    public void draw() {
        parent.pushMatrix();
        int skip = (int)(80 + soundReaction.getBandValueLog10(1) * 350);

        if(alpha1 > 1) {
            parent.stroke(alpha1, alpha1);
            parent.strokeWeight(4);
//        parent.stroke (map(soundReaction.getBandValueLog10(1), 0, 0.7f, 0, alpha1), alpha1);
            for (int x = 1; x < parent.width; x += skip) {
                for (int y = 1; y < parent.height; y += skip) {
                    parent.line(x, y, x, y + 2);
                }
            }
        }
        if(alpha2 > 1) {
            parent.stroke(alpha2, alpha2);
            parent.strokeWeight(map(skip, 80, 430, 2, 100 ));
//        parent.stroke (map(soundReaction.getBandValueLog10(1), 0, 0.7f, 0, alpha1), alpha1);
            for (int x = 1; x < parent.width; x += skip) {
                for (int y = 1; y < parent.height; y += 150) {
                    parent.line (x+50, y-62, x+60, y+270);
                }
            }
        }
        parent.popMatrix();
    }

    public void controllerChange(int channel, int number, int value) {
        if(number == 2) {
            alpha1 = (int)map(value, 0, 127, 0, 255);
        }
        if(number == 3) {
            alpha2 = (int)map(value, 0, 127, 0, 200);
        }
    }

    public void noteOn(int channel, int pitch, int velocity) {

    }
    public void noteOff(int channel, int pitch, int velocity) {

    }

}

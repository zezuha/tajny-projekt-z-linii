package secretLines.parts.abstractComposition;

import processing.core.PApplet;
import secretLines.media.soundReaction.SoundReaction;
import secretLines.midi.MidiReaction;
import secretLines.parts.part.Part;

import static processing.core.PApplet.map;
import static processing.core.PConstants.SQUARE;

public class AbstractCompositionPart extends Part implements MidiReaction {

    private SoundReaction soundReaction;
    private float kickSize, snareSize, hatSize;
    private int alpha;

    public AbstractCompositionPart(PApplet parent, SoundReaction soundReaction) {
        super(parent);
        this.soundReaction = soundReaction;
        alpha = 0;
    }

    public void draw() {
        parent.strokeCap(SQUARE);
        parent.strokeWeight(12);
        parent.translate(830, 290, -10);

        kickSize = soundReaction.getBandValue(0) * 64;
        snareSize = soundReaction.getBandValue(1) * 64;
        hatSize = soundReaction.getBandValue(2) * 64;

//        parent.stroke(kickSize * 9, snareSize * 10, hatSize * 11, alpha);
        parent.stroke(alpha, alpha);

        parent.strokeWeight(15);
        parent.line(0, -6 + kickSize, 300, -6 + kickSize);
        parent.strokeWeight(22);
        parent.line(0, 23, 200, 23);
        parent.strokeWeight(12);
        parent.line(0, 140, 200, 140);
        parent.strokeWeight(29);
        parent.line(0, 80, 112, 80);
        parent.strokeWeight(10);
        parent.line(0, 167 - kickSize, 106, 167 - kickSize);
        parent.strokeWeight(100);
        parent.line(200, 84, 300, 84);
        parent.strokeWeight(12);
        parent.line(0, 158 - kickSize, 200, 158 - kickSize);
        parent.strokeWeight(19);
        parent.line(0, 152, 200, 152);
        parent.strokeWeight(11);
        parent.line(0, 167, 106, 167);
        parent.strokeWeight(11);
        parent.line(0, 177, 106, 177);
        parent.strokeWeight(12);
        parent.line(0, 183, 106, 183);
        parent.strokeWeight(19);
        parent.line(105, 198, 300, 198);
        parent.strokeWeight(11);
        parent.line(105, 210, 300, 210);
        parent.strokeWeight(12);
        parent.line(105, 210, 300, 210);
        parent.strokeWeight(13);
        parent.line(0, 234 - snareSize, 300, 234 - snareSize);
        parent.strokeWeight(11);
        parent.line(0, 246, 160, 246);
        parent.strokeWeight(10);
        parent.line(0, 254, 160, 254);
        parent.strokeWeight(13);
        parent.line(160, 265, 300, 265);
        parent.strokeWeight(12);
        parent.line(0, 277 + snareSize, 300, 277 + snareSize);
        parent.strokeWeight(11);
        parent.line(0, 296, 300, 296);
        parent.strokeWeight(11);
        parent.line(0, 310 - snareSize, 300, 311 - snareSize);
        parent.strokeWeight(10);
        parent.line(0, 339 + hatSize, 300, 339 + hatSize);
        parent.strokeWeight(12);
        parent.line(0, 394 - hatSize, 300, 394 - hatSize);
        parent.strokeWeight(9);
        parent.line(0, 525 + hatSize, 300, 525 + hatSize);

    }

    public void controllerChange(int channel, int number, int value) {
       if(number == 0) {
           alpha = (int)map(value, 0, 127, 0, 255);
           System.out.println("abstract change ___________ " + alpha);
       }
    }

    public void noteOn(int channel, int pitch, int velocity) {

    }
    public void noteOff(int channel, int pitch, int velocity) {

    }
}

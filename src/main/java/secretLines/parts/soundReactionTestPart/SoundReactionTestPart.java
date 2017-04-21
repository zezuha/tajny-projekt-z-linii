package secretLines.parts.soundReactionTestPart;


import processing.core.PApplet;
import secretLines.media.soundReaction.SoundReaction;
import secretLines.parts.part.Part;

import static java.lang.Math.log10;
import static processing.core.PApplet.map;

public class SoundReactionTestPart extends Part {

    private SoundReaction soundReaction;
    private int alpha;

    public SoundReactionTestPart(PApplet parent, SoundReaction soundReaction) {
        super(parent);
        this.soundReaction = soundReaction;
        alpha = 255;
    }

    public void draw() {
        float bandWidth = parent.width / soundReaction.getBandsCount();
        parent.stroke(alpha, alpha);
        parent.fill(alpha, alpha);
        for (int i = 0; i < soundReaction.getBandsCount()- 1; i++) {
            for(int j = 0; j < 100; j+=4) {
//                parent.strokeWeight(3 - soundReaction.getBandValueLog10(i) * 3);
                parent.strokeWeight((3 - map(i, 0, 32, 0, 2)) * soundReaction.getBandValueLog10(3));
                float distance = map(i, 0, 32, 2, 10);
                if(i % 2 == 0) {
                    parent.line(
                            i * bandWidth,
                            j * 3 * soundReaction.getBandValueLog10(i) * distance - 300 + soundReaction.getBandValueLog10(i) * parent.height,
                            i * bandWidth + bandWidth,
                            j * distance - 300 + soundReaction.getBandValueLog10(i + 1) * parent.height);
                }
                else {
                    parent.line(
                            i * bandWidth,
                            j * distance - 300 + soundReaction.getBandValueLog10(i) * parent.height,
                            i * bandWidth + bandWidth,
                            j * 3 * soundReaction.getBandValueLog10(i + 1) * distance - 300 + soundReaction.getBandValueLog10(i + 1) * parent.height);
                }
            }
        }
    }

    @Override
    public void controllerChange(int channel, int number, int value) {
        switch (number) {
            case 6: {
                alpha = (int)map(value, 0, 127, 0, 255);
            }
            break;
        }
    }
}

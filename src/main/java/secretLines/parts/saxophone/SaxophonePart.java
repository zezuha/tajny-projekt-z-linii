package secretLines.parts.saxophone;


import processing.core.PApplet;
import processing.core.PShape;
import secretLines.media.soundReaction.SoundReaction;
import secretLines.parts.part.Part;

import java.util.ArrayList;
import java.util.List;

import static processing.core.PApplet.map;
import static secretLines.config.ConfigConstants.DISCOBOLUS_FILE_PATTERN;
import static secretLines.config.ConfigConstants.SAXOPHONE_FILE_PATTERN;

public class SaxophonePart extends Part {

    private SoundReaction soundReaction;
    private List<PShape> shapes;
    private float alpha;
    private float reactionFactor;

    public SaxophonePart(PApplet parent, SoundReaction soundReaction) {
        super(parent);
        this.soundReaction = soundReaction;
        shapes = new ArrayList<PShape>();
        for (int i = 0; i < 21; i++) {
            shapes.add(parent.loadShape(String.format(SAXOPHONE_FILE_PATTERN, i)));
        }
        alpha = 0;
        reactionFactor = 0.1f;
    }

    public void draw() {
        if(alpha > 0.1) {
            parent.pushMatrix();
            parent.pushStyle();
            parent.translate(1050, 660);
            parent.rotateZ(3.19f);
            parent.rotateY(0.24f);
            parent.translate(-1050, -660);
            for (int i = 0; i < shapes.size(); i++) {
                parent.pushMatrix();
                parent.rotate((soundReaction.getBandValueLog10(i) - 0.2f) * reactionFactor);
                parent.translate(parent.width / 2, parent.height / 2);
                parent.scale(alpha);
                parent.shape(shapes.get(i), 0, 0);
                parent.popMatrix();
            }
            parent.popStyle();
            parent.popMatrix();
        }
    }

    @Override
    public void controllerChange(int channel, int number, int value) {
        if (number == 20) {
            alpha = map(value, 0, 127, 0, 5);
        }

        if (number == 21) {
            reactionFactor = map(value, 0, 127, 0, 0.2f);
        }
    }
}

package secretLines.parts.soundReactionTestPart;


import processing.core.PApplet;
import secretLines.media.soundReaction.SoundReaction;
import secretLines.parts.part.Part;

public class SoundReactionTestPart extends Part {

    private SoundReaction soundReaction;

    public SoundReactionTestPart(PApplet parent, SoundReaction soundReaction) {
        super(parent);
        this.soundReaction = soundReaction;
    }

    public void draw() {
        soundReaction.update();
        parent.background(0);
        parent.fill(0, 0, 255);
        float bandWidth = parent.width / soundReaction.getBandsCount();
        for (int i = 0; i < soundReaction.getBandsCount(); i++) {
            parent.rect(i * bandWidth, 0, bandWidth, soundReaction.getBandValue(i) * 100 * parent.height);
        }

    }
}

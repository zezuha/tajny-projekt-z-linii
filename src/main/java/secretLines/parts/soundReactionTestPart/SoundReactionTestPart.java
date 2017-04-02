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
        float bandWidth = parent.width / soundReaction.getBandsCount();
        for (int i = 0; i < soundReaction.getBandsCount(); i++) {
            parent.fill(0, 0, 255);
            parent.rect(i * bandWidth, 0, bandWidth, soundReaction.getBandValue(i) * parent.height);
            parent.fill(255, 255, 255);
            parent.text(i, i * bandWidth, 10);
            parent.text(soundReaction.getBandValue(i), i * bandWidth, 20);
        }
    }
}

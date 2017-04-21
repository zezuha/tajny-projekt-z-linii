package secretLines.parts.waves;

import processing.core.PApplet;
import secretLines.media.soundReaction.SoundReaction;
import secretLines.parts.part.Part;

import static processing.core.PApplet.map;

public class WavesPart extends Part {

    private SoundReaction soundReaction;
    private int alpha;

    public WavesPart(PApplet parent, SoundReaction soundReaction) {
        super(parent);
        this.soundReaction = soundReaction;
        alpha = 255;
    }

    public void draw() {
        float bandWidth = parent.width / soundReaction.getBandsCount();
        parent.tint(alpha, alpha);
        parent.stroke(alpha, alpha);
        parent.fill(80, alpha);//30
        parent.pointLight(255, 255, 255, 800, 100, -1000);
        for (int i = 0; i < soundReaction.getBandsCount(); i++) {
            parent.stroke(0, alpha);
//            parent.strokeWeight(2 * soundReaction.getBandValueLog10(i));
            parent.pushMatrix();
            parent.translate(- i * 300, 500 + parent.height - i * 800 - soundReaction.getBandValueLog10(i) * 300, -i * 400 - 1500);
            parent.rotateY(-0.1f);
            parent.box(
                    parent.width + 10000 + soundReaction.getBandValueLog10(i) * 500,
                    800, 1000);
            parent.popMatrix();
        }
        parent.noTint();
    }

//    public void draw() {
//        float bandWidth = parent.width / soundReaction.getBandsCount();
//        parent.stroke(alpha, alpha);
//        parent.fill(alpha, alpha);
//        for (int i = 0; i < soundReaction.getBandsCount(); i++) {
//            parent.stroke(map(soundReaction.getBandValueLog10(i), 0.1f, 1, 0, 255), alpha);
//            parent.strokeWeight(2 * soundReaction.getBandValueLog10(i));
//            parent.line(
//                    0 - i * 300,
//                    parent.height - i * 100 + soundReaction.getBandValueLog10(i) * 500,
//                    -i * 200,
//                    parent.width + i * 300 + soundReaction.getBandValueLog10(i) * 200,
//                    parent.height - i * 100 + soundReaction.getBandValueLog10(i) * 600,
//                    -i * 400);
//            if(i < 20) {
//                parent.line(
//                        parent.width + i * 300 + soundReaction.getBandValueLog10(i) * 200,
//                        -1000,
//                        -i * 400,
//                        parent.width + i * 300 + soundReaction.getBandValueLog10(i) * 200,
//                        parent.height - i * 100 + soundReaction.getBandValueLog10(i) * 600,
//                        -i * 400);
//            }
//            else {
//                parent.line(
//                        parent.width,
//                        0,
//                        0,
//                        parent.width + i * 300 + soundReaction.getBandValueLog10(i) * 200,
//                        parent.height - i * 100 + soundReaction.getBandValueLog10(i) * 600,
//                        -i * 400);
//            }
//        }
//    }

    @Override
    public void controllerChange(int channel, int number, int value) {
        switch (number) {
            case 5: {
                System.out.println("STRIPES ALPHA CHANGE ___________________________");
                alpha = (int)map(value, 0, 127, 0, 255);
            }
            break;
        }
    }
}

package secretLines.parts.video3Dizer;

import processing.core.PApplet;
import processing.core.PImage;
import secretLines.config.ConfigConstants;
import secretLines.media.soundReaction.SoundReaction;
import secretLines.midi.MidiReaction;
import secretLines.parts.utils.movie.BasicMoviePart;

import static processing.core.PApplet.map;
import static processing.core.PConstants.*;

public class Video3Dizer extends BasicMoviePart implements MidiReaction {

    private PImage movieImg;
    private SoundReaction soundReaction;

    private int skip;
    private float glitchLevel;
    private final int MAX_Z = 1000;
    private float maxZ;

    private int base1Alpha;
    private int base2Alpha;

    private int layer1Alpha;
    private int layer2Alpha;
    private int layer3Alpha;
    private float rotateY;
    private float rotateZ;

    public Video3Dizer(PApplet parent, SoundReaction soundReaction) {
        super(parent, ConfigConstants.VIDEO_PATH,
                ConfigConstants.LAYER1_PATH, ConfigConstants.LAYER2_PATH,
                ConfigConstants.LAYER3_PATH);
        this.soundReaction = soundReaction;
        skip = 10;
        base1Alpha = 255;
        base2Alpha = 0;
        layer1Alpha = 0;
        layer2Alpha = 0;
        layer3Alpha = 0;
        rotateY = 0;
        rotateZ = 0;
    }

    @Override
    public void start() {
        super.start();
        moviePlayer.speed(0, 5);
    }

    @Override
    public void finish() {
        super.finish();
    }

    private void updateMovie() {
        moviePlayer.readFrame();
        movieImg = moviePlayer.getMovie();
        movieImg.loadPixels();
    }

    private void update() {
        updateMovie();
        maxZ = map(soundReaction.getBandValue(1), 0.2f, 0.6f, 0, MAX_Z);
        rotateZ = map(soundReaction.getBandValue(2), 0, 0.8f, 0.4f, 1);
    }

    private void drawStripes() {
//        parent.rotateX(-map(maxZ, 0, MAX_Z, 0, PI/12));
        skip = 10;
        parent.noFill();
        parent.stroke(120, 80, 213, base2Alpha);
        parent.strokeWeight(1);
        for (int y = 0; y < movieImg.height - skip; y += skip) {
            parent.rotateY(rotateZ);
            parent.beginShape(TRIANGLE_STRIP);
            for (int x = 0; x < movieImg.width - skip; x += skip) {

                int y1 = y + skip;
                int index = y * movieImg.width + x;
                int index1 = y1 * movieImg.width + x;
                float z = map(parent.brightness(movieImg.pixels[index]), 0, 255, 0, maxZ);
                float z1 = map(parent.brightness(movieImg.pixels[index1]), 0, 255, 0, maxZ);
                int tx = x;
                int ty = y;
//                parent.fill(movieImg.pixels[index]);
                if(parent.random(100) < glitchLevel) {
                    tx += parent.random(80) - 40;
                    ty += parent.random(80) - 40;
                    parent.vertex(x, y, z);
                    parent.vertex(x, y1, z1);

                }
                parent.vertex(tx, ty, z);
                parent.vertex(tx, y1, z1);
            }
            parent.endShape();
        }
    }

    private void drawGreenMesh() {
        skip = 15;
        parent.stroke(0, 255, 0, base1Alpha);
        parent.strokeWeight(3);
        for (int y = 0; y < movieImg.height - skip; y += skip) {
            parent.rotateY(rotateY);
            for (int x = 0; x < movieImg.width - skip; x += skip) {
                int x1 = x + skip;
                int index = y * movieImg.width + x;
                int index1 = y * movieImg.width + x1;
                float z = map(parent.brightness(movieImg.pixels[index]), 0, 255, 0, maxZ);
                float z1 = map(parent.brightness(movieImg.pixels[index1]), 0, 255, 0, maxZ);
                parent.beginShape(LINES);
                if (x % 2 == 0) {
                    parent.stroke(255, 0, 0, base1Alpha);
                }
                else {
                    parent.stroke(0, 255, 0, base1Alpha);
                }
                parent.vertex(x, y, z);
                if (x % 2 == 0) {
                    parent.stroke(0, 255, 0, base1Alpha);
                }
                else {
                    parent.stroke(255, 0, 0, base1Alpha);
                }
                parent.vertex(x1, y, z1);
                parent.endShape();
            }
        }
    }

    private void drawBoxes() {
        skip = 20;
        maxZ = MAX_Z - maxZ;
        parent.rotateX(-map(maxZ, 0, MAX_Z, 0, PI/12));
        parent.stroke(255, 0, 0, 255 - parent.random(100));
        for (int y = 0; y < movieImg.height - skip; y += skip) {
            for (int x = 0; x < movieImg.width - skip; x += skip) {
                int index = y * movieImg.width + x;
                float z = map(parent.brightness(movieImg.pixels[index]), 0, 255, 0,  maxZ);
                float boxSize = 40 - map(z, 0, maxZ, 40, 10);
                parent.pushMatrix();
                parent.translate(x - boxSize / 2, y - boxSize / 2, z - boxSize / 2);
                parent.box(boxSize);
                parent.popMatrix();
            }
        }
    }

    private void drawMeshAndBoxes() {
        parent.pushStyle();
        parent.noFill();
        parent.translate(100, 100, -1000);//400

        parent.pushMatrix();
        parent.pushStyle();

        drawGreenMesh();

        parent.popStyle();
        parent.popMatrix();
        parent.pushMatrix();
        parent.pushStyle();

        drawStripes();

        parent.popStyle();
        parent.popMatrix();

        parent.popStyle();
    }

    @Override
    public void draw() {
        parent.background(0);
        update();
        parent.rotateX(map(parent.mouseY, 0, parent.height, 0, 1.7f));
        drawMeshAndBoxes();
        parent.blendMode(SCREEN);

        parent.tint(layer1Alpha, layer1Alpha);
        moviePlayer.drawMovieFullscreen(1);
        parent.noTint();

        parent.tint(layer2Alpha, layer2Alpha);
        moviePlayer.drawMovieFullscreen(2);
        parent.noTint();

        parent.tint(layer3Alpha, layer3Alpha);
        moviePlayer.drawMovieFullscreen(3);
        parent.noTint();
    }

    public void controllerChange(int channel, int number, int value) {
        switch (number) {
            case 0: {
                layer1Alpha = (int)map(value, 0, 127, 0, 255);
            }
            break;
            case 1: {
                layer2Alpha = (int)map(value, 0, 127, 0, 255);
            }
            break;
            case 2: {
                layer3Alpha = (int)map(value, 0, 127, 0, 255);
            }
            break;
            case 3: {
                rotateY = map(value, 0, 127, 0, 0.4f);
            }
            break;
            case 4: {
                rotateZ = map(value, 0, 127, 0, 6.28f);
            }
            break;
            case 16: {
                base1Alpha = (int)map(value, 0, 127, 0, 255);
            }
            break;
            case 17: {
                base2Alpha = (int)map(value, 0, 127, 0, 255);
            }
            break;
        }
    }

    public void noteOn(int channel, int pitch, int velocity) {

    }
    public void noteOff(int channel, int pitch, int velocity) {

    }
}
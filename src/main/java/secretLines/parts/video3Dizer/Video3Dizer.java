package secretLines.parts.video3Dizer;

import processing.core.PApplet;
import processing.core.PImage;
import secretLines.config.ConfigConstants;
import secretLines.media.soundReaction.SoundReaction;
import secretLines.parts.utils.movie.BasicMoviePart;

import static processing.core.PApplet.map;
import static processing.core.PApplet.max;
import static processing.core.PConstants.*;

public class Video3Dizer extends BasicMoviePart {

    private PImage movieImg;
    private SoundReaction soundReaction;

    private int skip;
    private float maxZLimit;
    private float maxZ;
    private int currentMovieIndex;
    private int alpha;

    public Video3Dizer(PApplet parent, SoundReaction soundReaction) {
        super(parent, ConfigConstants.GRADIENT1_PATH,
                ConfigConstants.GRADIENT2_PATH, ConfigConstants.GRADIENT3_PATH);
        this.soundReaction = soundReaction;
        skip = 10;
        maxZLimit = 1000;
        alpha = 0;
        currentMovieIndex = 0;
    }

    @Override
    public void start() {
        super.start();
        currentMovieIndex = 0;
        moviePlayer.loop(currentMovieIndex);
    }

    @Override
    public void finish() {
        super.finish();
    }

    private void updateMovie() {
        moviePlayer.readFrame();
        movieImg = moviePlayer.getMovie(currentMovieIndex);
        movieImg.loadPixels();
    }

    private void update() {
        updateMovie();
        maxZ = map(soundReaction.getBandValueLog10(1), 0.2f, 0.6f, 0, maxZLimit);
    }

    private void drawMesh() {
        skip = 15;
        parent.stroke(alpha, alpha);
        parent.strokeWeight(3);
        for (int y = 0; y < movieImg.height - skip; y += skip) {
            for (int x = 0; x < movieImg.width - skip; x += skip) {
                int x1 = x + skip;
                int index = y * movieImg.width + x;
                int index1 = y * movieImg.width + x1;
                float z = map(parent.brightness(movieImg.pixels[index]), 0, 255, 0, maxZ);
                float z1 = map(parent.brightness(movieImg.pixels[index1]), 0, 255, 0, maxZ);
                parent.beginShape(LINES);
                parent.vertex(x, y, z);
                parent.vertex(x1, y, z1);
                parent.endShape();
            }
        }
    }

    @Override
    public void draw() {
        update();
        if (alpha > 1) {
            parent.pushMatrix();
            parent.pushStyle();
            parent.noFill();
            parent.translate(100, 100, map(parent.mouseY, 0, parent.height, 0, 1000));//-1000
            drawMesh();
            parent.popStyle();
            parent.popMatrix();
        }
    }

    @Override
    public void controllerChange(int channel, int number, int value) {
        switch (number) {
            case 0: {
                alpha = (int) map(value, 0, 127, 0, 255);
            }
            case 1: {
                maxZLimit = map(value, 0, 127, 1, 1000);
            }
            break;
            case 61: { //left marker arrow
                moviePlayer.stop(currentMovieIndex);
                if (--currentMovieIndex < 0) {
                    currentMovieIndex = moviePlayer.getMoviesCount() - 1;
                }
                moviePlayer.loop(currentMovieIndex);
            }
            break;
            case 62: { //right marker arrow
                moviePlayer.stop(currentMovieIndex);
                if (++currentMovieIndex > moviePlayer.getMoviesCount() - 1) {
                    currentMovieIndex = 0;
                }
                moviePlayer.loop(currentMovieIndex);
            }
            break;
        }
    }
}
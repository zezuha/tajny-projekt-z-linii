package secretLines.parts.videoLayers.layer;

import org.omg.PortableServer.THREAD_POLICY_ID;
import processing.core.PApplet;
import processing.video.Movie;

import static processing.core.PApplet.map;
import static processing.core.PConstants.CENTER;

public class VideoLayer {

    private PApplet parent;
    private Movie movie;
    private int alpha;
    private float scale;

    public VideoLayer(PApplet parent, Movie movie, int alpha) {
        this.parent = parent;
        this.movie = movie;
        this.alpha = alpha;
        this.scale = 1;
    }

    public VideoLayer(PApplet parent, Movie movie) {
        this(parent, movie, 0);
    }

    public void setAlpha(int alpha) {
        this.alpha = alpha;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public void draw() {
        parent.pushStyle();
        parent.imageMode(CENTER);
//        parent.scale(scale);
        parent.tint(alpha, alpha);
        parent.image(movie, parent.width/2, parent.height/2, parent.width * scale, parent.height * scale);
        parent.noTint();
        parent.popStyle();
    }

    public void controllerChange(int value) {
        alpha = (int)map(value, 0, 127, 0, 255);
    }
}

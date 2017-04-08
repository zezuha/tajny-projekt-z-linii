package secretLines.parts.videoLayers.layer;

import org.omg.PortableServer.THREAD_POLICY_ID;
import processing.core.PApplet;
import processing.video.Movie;

import static processing.core.PApplet.map;

public class VideoLayer {

    private PApplet parent;
    private Movie movie;
    private int alpha;

    public VideoLayer(PApplet parent, Movie movie, int alpha) {
        this.parent = parent;
        this.movie = movie;
        this.alpha = alpha;
    }

    public VideoLayer(PApplet parent, Movie movie) {
        this(parent, movie, 0);
    }

    public void setAlpha(int alpha) {
        this.alpha = alpha;
    }

    public void draw() {
        parent.tint(alpha, alpha);
        parent.image(movie, 0, 0, parent.width, parent.height);
        parent.noTint();
    }

    public void controllerChange(int value) {
        alpha = (int)map(value, 0, 127, 0, 255);
    }
}

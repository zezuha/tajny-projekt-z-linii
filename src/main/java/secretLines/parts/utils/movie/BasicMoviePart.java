package secretLines.parts.utils.movie;

import secretLines.parts.part.Part;
import secretLines.media.moviePlayer.MoviePlayer;
import processing.core.PApplet;

public class BasicMoviePart extends Part {

    protected MoviePlayer moviePlayer;

    public BasicMoviePart(PApplet parent, String... moviePath) {
        super(parent);
        moviePlayer = new MoviePlayer(parent);
        moviePlayer.loadMovies(moviePath);
    }

    @Override
    public void start() {
        super.start();
        moviePlayer.loop();

    }

    @Override
    public void finish() {
        super.finish();
        moviePlayer.stop();
    }

    protected void checkFinish() {
        if(moviePlayer.isFinished()) {
            finish();
        }
    }

    public void draw() {
        moviePlayer.readFrame();
        moviePlayer.drawMovieFullscreen(0);
        checkFinish();
    }
}

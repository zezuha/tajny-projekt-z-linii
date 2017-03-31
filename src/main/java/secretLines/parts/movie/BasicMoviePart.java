package secretLines.parts.movie;

import secretLines.parts.part.Part;
import secretLines.utils.moviePlayer.MoviePlayer;
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
        moviePlayer.play();

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

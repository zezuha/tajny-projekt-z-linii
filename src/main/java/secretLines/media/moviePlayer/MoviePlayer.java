package secretLines.media.moviePlayer;

import processing.core.PApplet;
import processing.video.Movie;

import java.util.ArrayList;
import java.util.List;

public class MoviePlayer {

    protected PApplet parent;
    protected List<Movie> movies;
    protected int maxDuration;
    protected float musicVolume; //

    public MoviePlayer(PApplet parent) {
        this.parent = parent;
        movies = new ArrayList<>();
        maxDuration = 3600;
        musicVolume = 0;
    }

    public MoviePlayer(PApplet parent, float musicVolume) {
        this(parent);
        this.musicVolume = musicVolume;
    }

    public void loadMovies(String... paths) {
        for(String path: paths) {
            movies.add(new Movie(parent, path));
        }
        for(Movie movie: movies) {
            movie.volume(0);
        }
    }

    //max duration in seconds
    public void setMaxDuration(int maxDuration) {
        this.maxDuration = maxDuration;
    }

    public int getMoviesCount() {
        return movies.size();
    }

    public void readFrame() {
        for(Movie movie: movies) {
            if (movie.available()) {
                movie.read();
            }
        }
    }

    public void play(int movieIndex) {
        getMovie(movieIndex).play();
        getMovie(movieIndex).volume(0);
    }

    public void play() {
        for(Movie movie: movies) {
            movie.play();
            movie.volume(0);
        }
    }

    public void loop(int movieIndex) {
        getMovie(movieIndex).loop();
        getMovie(movieIndex).volume(0);
    }

    public void loop() {
        for(Movie movie: movies) {
            movie.loop();
            movie.volume(0);
        }
    }

    public void stop(int movieIndex) {
        getMovie(movieIndex).stop();
        getMovie(movieIndex).volume(0);
    }

    public void stop() {
        for(Movie movie: movies) {
            movie.volume(0);
            movie.stop();
        }
    }

    public void jumpTo(int movieIndex, int seconds) {
        getMovie(movieIndex).jump(seconds);
    }

    public void speed(int movieIndex, float speed) {
        getMovie(movieIndex).speed(speed);
    }

    public boolean isFinished() {
        for(Movie movie: movies) {
            if(movie.time() >= movie.duration() || movie.time() > maxDuration) {
                return true;
            }
        }
        return false;
    }

    public void drawMovieFullscreen(int movieIndex) {
        parent.image(getMovie(movieIndex), 0, 0, parent.width, parent.height);
    }

    public void drawMovieOnPosition(int movieIndex, float x, float y) {
        parent.image(getMovie(movieIndex), x, y);
    }

    public void drawMovieOnPosition(int movieIndex, float x, float y, float width, float height) {
        parent.image(getMovie(movieIndex), x, y, width, height);
    }

    public Movie getMovie(int movieIndex) {
        return movies.get(movieIndex);
    }

    public Movie getMovie() {
        return getMovie(0);
    }

    public List<Movie> getMovies() {
        return movies;
    }
}

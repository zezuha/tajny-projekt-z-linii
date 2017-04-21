package secretLines.parts.videoLayers;

import processing.core.PApplet;
import processing.video.Movie;
import secretLines.config.ConfigConstants;
import secretLines.midi.MidiReaction;
import secretLines.parts.utils.movie.BasicMoviePart;
import secretLines.parts.videoLayers.layer.VideoLayer;

import java.util.ArrayList;
import java.util.List;

import static processing.core.PApplet.map;

public class VideoLayers extends BasicMoviePart implements MidiReaction {

    private List<VideoLayer> videoLayers;
    private int controlNumberOffset;

    public VideoLayers(PApplet parent, int controlNumberOffset, String... moviePath) {
        super(parent, moviePath);
        videoLayers = new ArrayList<>();
        for (Movie movie: moviePlayer.getMovies()) {
            videoLayers.add(new VideoLayer(parent, movie));
        }
        this.controlNumberOffset = controlNumberOffset;
    }

    public VideoLayer getVideoLayer(int index) {
        return videoLayers.get(index);
    }

    @Override
    public void start() {
        super.start();
        for(VideoLayer videoLayer: videoLayers) {
            videoLayer.setAlpha(0);
        }
    }

    @Override
    public void draw() {
        moviePlayer.readFrame();
        for(VideoLayer videoLayer: videoLayers) {
            videoLayer.draw();
        }
    }

    public void controllerChange(int channel, int number, int value) {
        for (int i = 0; i < videoLayers.size(); i++) {
            if(number == i * 2 + controlNumberOffset) {
                getVideoLayer(i).setAlpha(
                        (int)map(value, 0, 127, 0, 255));
            }
            if(number == i * 2 + controlNumberOffset + 1) {
                getVideoLayer(i).setScale(
                        map(value, 0, 127, 0.2f, 4));
            }
        }
    }

    public void noteOn(int channel, int pitch, int velocity) {

    }
    public void noteOff(int channel, int pitch, int velocity) {

    }
}

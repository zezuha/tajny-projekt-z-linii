package secretLines.parts.videoLayers;

import processing.core.PApplet;
import processing.video.Movie;
import secretLines.config.ConfigConstants;
import secretLines.midi.MidiReaction;
import secretLines.parts.utils.movie.BasicMoviePart;
import secretLines.parts.videoLayers.layer.VideoLayer;

import java.util.ArrayList;
import java.util.List;

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
            if(number == i + controlNumberOffset) {
                getVideoLayer(i).controllerChange(value);
                System.out.println("video layer " + i + " -  control change");
            }
        }
    }

    public void noteOn(int channel, int pitch, int velocity) {

    }
    public void noteOff(int channel, int pitch, int velocity) {

    }
}

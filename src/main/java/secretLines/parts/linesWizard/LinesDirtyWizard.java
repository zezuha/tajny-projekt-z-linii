package secretLines.parts.linesWizard;

import processing.core.PApplet;
import secretLines.config.ConfigConstants;
import secretLines.media.soundReaction.SoundReaction;
import secretLines.midi.MidiReaction;
import secretLines.parts.abstractComposition.AbstractCompositionPart;
import secretLines.parts.dots.DotsPart;
import secretLines.parts.part.Part;
import secretLines.parts.soundReactionTestPart.SoundReactionTestPart;
import secretLines.parts.video3Dizer.Video3Dizer;
import secretLines.parts.videoLayers.VideoLayers;
import secretLines.parts.waves.WavesPart;


public class LinesDirtyWizard extends Part {

    private DotsPart dotsPart;
    private Video3Dizer video3Dizer;
    private SoundReactionTestPart soundReactionTestPart;
    private VideoLayers videoLayers0;
    private VideoLayers videoLayers1;
    private VideoLayers videoLayers2;
    private VideoLayers videoLayers3;

    public LinesDirtyWizard(PApplet parent, SoundReaction soundReaction) {
        super(parent);
        dotsPart = new DotsPart(parent, soundReaction);
        video3Dizer = new Video3Dizer(parent, soundReaction);
        soundReactionTestPart = new SoundReactionTestPart(parent, soundReaction);
        videoLayers0 = new VideoLayers(parent, 16,
                ConfigConstants.LAYER1_PATH,
                ConfigConstants.LAYER2_PATH
                );
        videoLayers1 = new VideoLayers(parent, 16,
                ConfigConstants.LAYER7_PATH,
                ConfigConstants.LAYER8_PATH
        );
        videoLayers2 = new VideoLayers(parent, 16,
                ConfigConstants.LAYER13_PATH,
                ConfigConstants.LAYER14_PATH
        );
        videoLayers3 = new VideoLayers(parent, 16,
                ConfigConstants.LAYER6_PATH,
                ConfigConstants.LAYER12_PATH
        );
    }

    @Override
    public void start() {
        super.start();
        dotsPart.start();
        soundReactionTestPart.start();
        video3Dizer.start();
        videoLayers0.start();
    }

    @Override
    public void finish() {
        super.finish();
        dotsPart.finish();
        video3Dizer.finish();
        soundReactionTestPart.finish();
        if(videoLayers0.isActive()) {
            videoLayers0.finish();
        }
        if(videoLayers1.isActive()) {
            videoLayers1.finish();
        }
        if(videoLayers2.isActive()) {
            videoLayers2.finish();
        }
        if(videoLayers3.isActive()) {
            videoLayers3.finish();
        }
    }

    public void draw() {
        parent.background(0);
        if(dotsPart.isActive()) {
            dotsPart.draw();
        }

        if(video3Dizer.isActive()) {
            video3Dizer.draw();
        }

        parent.pushMatrix();
        if(soundReactionTestPart.isActive()) {
            soundReactionTestPart.draw();
        }
        parent.popMatrix();
        parent.pushMatrix();
        if(videoLayers0.isActive()) {
            videoLayers0.draw();
        }
        if(videoLayers1.isActive()) {
            videoLayers1.draw();
        }
        if(videoLayers2.isActive()) {
            videoLayers2.draw();
        }
        if(videoLayers3.isActive()) {
            videoLayers3.draw();
        }
        parent.popMatrix();
    }

    @Override
    public void controllerChange(int channel, int number, int value) {
        dotsPart.controllerChange(channel, number, value);
        video3Dizer.controllerChange(channel, number, value);
        soundReactionTestPart.controllerChange(channel, number, value);
        videoLayers0.controllerChange(channel, number, value);
        videoLayers1.controllerChange(channel, number, value);
        videoLayers2.controllerChange(channel, number, value);
        videoLayers3.controllerChange(channel, number, value);
        if(number == 43 && value == 127) { // previous track button
            videoLayers0.finish();
            videoLayers2.finish();
            videoLayers3.finish();
            videoLayers1.start();
            System.out.println("layers1 active");
        }
        if(number == 44 && value == 127) { //next track button
            videoLayers1.finish();
            videoLayers2.finish();
            videoLayers3.finish();
            videoLayers0.start();
            System.out.println("layers0 active");
        }
        if(number == 42 && value == 127) { //next track button
            videoLayers0.finish();
            videoLayers1.finish();
            videoLayers3.finish();
            videoLayers2.start();
            System.out.println("layers2 active");
        }
        if(number == 41 && value == 127) { //next track button
            videoLayers0.finish();
            videoLayers1.finish();
            videoLayers2.finish();
            videoLayers3.start();
            System.out.println("layers2 active");
        }
    }

    @Override
    public void noteOn(int channel, int pitch, int velocity) {
        video3Dizer.noteOn(channel, pitch, velocity);
        videoLayers0.noteOn(channel, pitch, velocity);

    }

    @Override
    public void noteOff(int channel, int pitch, int velocity) {
        video3Dizer.noteOff(channel, pitch, velocity);
        videoLayers0.noteOff(channel, pitch, velocity);
    }
}

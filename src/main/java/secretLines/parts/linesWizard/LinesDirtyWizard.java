package secretLines.parts.linesWizard;

import processing.core.PApplet;
import secretLines.config.ConfigConstants;
import secretLines.media.soundReaction.SoundReaction;
import secretLines.midi.MidiReaction;
import secretLines.parts.abstractComposition.AbstractCompositionPart;
import secretLines.parts.part.Part;
import secretLines.parts.video3Dizer.Video3Dizer;
import secretLines.parts.videoLayers.VideoLayers;


public class LinesDirtyWizard extends Part implements MidiReaction {

    private AbstractCompositionPart abstractCompositionPart;
    private Video3Dizer video3Dizer;
    private VideoLayers videoLayers0;
    private VideoLayers videoLayers1;
//    private BlendModesWizard blendModesWizard;

    public LinesDirtyWizard(PApplet parent, SoundReaction soundReaction) {
        super(parent);
        abstractCompositionPart = new AbstractCompositionPart(parent, soundReaction);
        video3Dizer = new Video3Dizer(parent, soundReaction);
        videoLayers0 = new VideoLayers(parent, 16,
                ConfigConstants.LAYER1_PATH,
                ConfigConstants.LAYER2_PATH,
                ConfigConstants.LAYER3_PATH
                );
        videoLayers1 = new VideoLayers(parent, 16,
                ConfigConstants.LAYER4_PATH,
                ConfigConstants.LAYER5_PATH,
                ConfigConstants.LAYER6_PATH
        );
//        blendModesWizard = new BlendModesWizard(parent);
    }

    @Override
    public void start() {
        super.start();
        abstractCompositionPart.start();
        video3Dizer.start();
        videoLayers0.start();
    }

    @Override
    public void finish() {
        super.finish();
        abstractCompositionPart.finish();
        video3Dizer.finish();
        if(videoLayers0.isActive()) {
            videoLayers0.finish();
        }
        if(videoLayers1.isActive()) {
            videoLayers1.finish();
        }
    }

    public void draw() {
        parent.background(0);
//        blendModesWizard.setBlendMode();
        parent.pushMatrix();
        if(abstractCompositionPart.isActive()) {
            abstractCompositionPart.draw();
        }
        parent.popMatrix();
        parent.pushMatrix();
        if(video3Dizer.isActive()) {
            video3Dizer.draw();
        }
        parent.popMatrix();
        parent.pushMatrix();
        if(videoLayers0.isActive()) {
            videoLayers0.draw();
        }
        if(videoLayers1.isActive()) {
            videoLayers1.draw();
        }
        parent.popMatrix();
    }

    public void controllerChange(int channel, int number, int value) {
        abstractCompositionPart.controllerChange(channel, number, value);
        video3Dizer.controllerChange(channel, number, value);
        videoLayers0.controllerChange(channel, number, value);
        videoLayers1.controllerChange(channel, number, value);
        if(number == 43 && value == 127) { // previous track button
            videoLayers0.finish();
            videoLayers1.start();
            System.out.println("layers1 active");
        }
        if(number == 44 && value == 127) { //next track button
            videoLayers1.finish();
            videoLayers0.start();
            System.out.println("layers0 active");
        }
    }

    public void noteOn(int channel, int pitch, int velocity) {
        video3Dizer.noteOn(channel, pitch, velocity);
        videoLayers0.noteOn(channel, pitch, velocity);

    }
    public void noteOff(int channel, int pitch, int velocity) {
        video3Dizer.noteOff(channel, pitch, velocity);
        videoLayers0.noteOff(channel, pitch, velocity);
    }
}

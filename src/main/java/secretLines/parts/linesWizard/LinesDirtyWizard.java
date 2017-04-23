package secretLines.parts.linesWizard;

import processing.core.PApplet;
import secretLines.config.ConfigConstants;
import secretLines.media.soundReaction.SoundReaction;
import secretLines.parts.discobolus.DiscobolusPart;
import secretLines.parts.dots.DotsPart;
import secretLines.parts.part.Part;
import secretLines.parts.saxophone.SaxophonePart;
import secretLines.parts.soundReactionTestPart.SoundReactionTestPart;
import secretLines.parts.video3Dizer.Video3Dizer;
import secretLines.parts.videoLayers.VideoLayers;

import java.util.ArrayList;
import java.util.List;

import static processing.core.PApplet.lerp;
import static processing.core.PApplet.map;


public class LinesDirtyWizard extends Part {

    private SoundReaction soundReaction;
    private DotsPart dotsPart;
    private Video3Dizer video3Dizer;
    private SoundReactionTestPart soundReactionTestPart;
    private DiscobolusPart discobolusPart;
    private SaxophonePart saxophonePart;
    private List<VideoLayers> videoLayersList;
    private int activeLayerIndex;
    private float worldRotation;

    public LinesDirtyWizard(PApplet parent, SoundReaction soundReaction) {
        super(parent);
        this.soundReaction = soundReaction;
        dotsPart = new DotsPart(parent, soundReaction);
        video3Dizer = new Video3Dizer(parent, soundReaction);
        soundReactionTestPart = new SoundReactionTestPart(parent, soundReaction);
        discobolusPart = new DiscobolusPart(parent, soundReaction);
        saxophonePart = new SaxophonePart(parent, soundReaction);
        videoLayersList = new ArrayList<>();
        activeLayerIndex = 0;
        initVideoLayers();
        worldRotation = 0;
    }

    private void initVideoLayers() {
        videoLayersList.add(new VideoLayers(parent, 16,
                ConfigConstants.LAYER1_PATH,
                ConfigConstants.LAYER2_PATH
        ));
        videoLayersList.add(new VideoLayers(parent, 16,
                ConfigConstants.LAYER7_PATH,
                ConfigConstants.LAYER8_PATH
        ));
        videoLayersList.add(new VideoLayers(parent, 16,
                ConfigConstants.LAYER13_PATH,
                ConfigConstants.LAYER14_PATH
        ));
        videoLayersList.add(new VideoLayers(parent, 16,
                ConfigConstants.LAYER6_PATH,
                ConfigConstants.LAYER12_PATH
        ));
        videoLayersList.add(new VideoLayers(parent, 16,
                ConfigConstants.LAYER15_PATH,
                ConfigConstants.LAYER16_PATH
        ));
        videoLayersList.add(new VideoLayers(parent, 16,
                ConfigConstants.LAYER17_PATH,
                ConfigConstants.LAYER9_PATH
        ));
        videoLayersList.add(new VideoLayers(parent, 16,
                ConfigConstants.LAYER10_PATH,
                ConfigConstants.LAYER11_PATH
        ));
        videoLayersList.add(new VideoLayers(parent, 16,
                ConfigConstants.LAYER5_PATH,
                ConfigConstants.LAYER6_PATH
        ));
        videoLayersList.add(new VideoLayers(parent, 16,
                ConfigConstants.LAYER18_PATH,
                ConfigConstants.LAYER19_PATH
        ));

        videoLayersList.add(new VideoLayers(parent, 16,
                ConfigConstants.LAYER20_PATH,
                ConfigConstants.LAYER21_PATH
        ));
    }

    @Override
    public void start() {
        super.start();
        dotsPart.start();
        soundReactionTestPart.start();
        video3Dizer.start();
        discobolusPart.start();
        saxophonePart.start();
        activeLayerIndex = 0;
        videoLayersList.get(activeLayerIndex).start();

    }

    @Override
    public void finish() {
        super.finish();
        dotsPart.finish();
        video3Dizer.finish();
        soundReactionTestPart.finish();
        discobolusPart.finish();
        saxophonePart.start();
        videoLayersList.get(activeLayerIndex).finish();
    }

    public void draw() {
        parent.pushMatrix();
        videoLayersKeyboardAction();
        parent.background(0);

//        if (soundReaction.getBandValueLog10(2) > 0.8f) {
//            worldRotation += 0.3;
//        }
        parent.translate(parent.width / 2, parent.height / 2, 0);
        parent.rotateY(worldRotation);
        parent.translate(-parent.width / 2, -parent.height / 2, 0);
//        worldRotation = lerp(worldRotation, 0, 0.99f);

        if (dotsPart.isActive()) {
            dotsPart.draw();
        }

        if (video3Dizer.isActive()) {
            video3Dizer.draw();
        }

        if (soundReactionTestPart.isActive()) {
            soundReactionTestPart.draw();
        }

        if (discobolusPart.isActive()) {
            discobolusPart.draw();
        }

        if (saxophonePart.isActive()) {
            saxophonePart.draw();
        }

        parent.pushMatrix();
        videoLayersList.get(activeLayerIndex).draw();
        parent.popMatrix();

        parent.popMatrix();
    }

    private void videoLayersKeyboardAction() {
        if (parent.keyPressed) {
            switch (parent.key) {
                case '1': {
                    videoLayersList.get(activeLayerIndex).finish();
                    activeLayerIndex = 0;
                    videoLayersList.get(activeLayerIndex).start();
                }
                break;
                case '2': {
                    videoLayersList.get(activeLayerIndex).finish();
                    activeLayerIndex = 1;
                    videoLayersList.get(activeLayerIndex).start();
                }
                break;
                case '3': {
                    videoLayersList.get(activeLayerIndex).finish();
                    activeLayerIndex = 2;
                    videoLayersList.get(activeLayerIndex).start();
                }
                break;
                case '4': {
                    videoLayersList.get(activeLayerIndex).finish();
                    activeLayerIndex = 3;
                    videoLayersList.get(activeLayerIndex).start();
                }
                break;
                case '5': {
                    videoLayersList.get(activeLayerIndex).finish();
                    activeLayerIndex = 4;
                    videoLayersList.get(activeLayerIndex).start();
                }
                break;
                case '6': {
                    videoLayersList.get(activeLayerIndex).finish();
                    activeLayerIndex = 5;
                    videoLayersList.get(activeLayerIndex).start();
                }
                break;
                case '7': {
                    videoLayersList.get(activeLayerIndex).finish();
                    activeLayerIndex = 6;
                    videoLayersList.get(activeLayerIndex).start();
                }
                break;
                case '8': {
                    videoLayersList.get(activeLayerIndex).finish();
                    activeLayerIndex = 7;
                    videoLayersList.get(activeLayerIndex).start();
                }
                break;
                case '9': {
                    videoLayersList.get(activeLayerIndex).finish();
                    activeLayerIndex = 8;
                    videoLayersList.get(activeLayerIndex).start();
                }
                break;
                case '0': {
                    videoLayersList.get(activeLayerIndex).finish();
                    activeLayerIndex = 9;
                    videoLayersList.get(activeLayerIndex).start();
                }
                break;
            }
        }
    }

    @Override
    public void controllerChange(int channel, int number, int value) {
        dotsPart.controllerChange(channel, number, value);
        video3Dizer.controllerChange(channel, number, value);
        soundReactionTestPart.controllerChange(channel, number, value);
        discobolusPart.controllerChange(channel, number, value);
        saxophonePart.controllerChange(channel, number, value);
        videoLayersList.get(activeLayerIndex).controllerChange(channel, number, value);

        if (number == 22) {
            worldRotation = map(value, 0, 128, 0, 6.28f);
        }
    }
}

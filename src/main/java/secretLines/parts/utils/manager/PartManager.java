package secretLines.parts.utils.manager;

import processing.core.PApplet;
import processing.core.PConstants;
import secretLines.config.ConfigConstants;
import secretLines.media.soundReaction.SoundReaction;
import secretLines.midi.MidiReaction;
import secretLines.parts.abstractComposition.AbstractCompositionPart;
import secretLines.parts.linesWizard.LinesDirtyWizard;
import secretLines.parts.part.Part;
import secretLines.parts.soundReactionTestPart.SoundReactionTestPart;
import secretLines.parts.utils.name.*;
import secretLines.parts.video3Dizer.Video3Dizer;
import secretLines.parts.videoLayers.VideoLayers;
import secretLines.parts.waves.WavesPart;

import java.util.HashMap;

import static processing.core.PApplet.map;
import static processing.core.PConstants.SCREEN;

public class PartManager implements MidiReaction {

    private PApplet parent;

    private HashMap<PartName, Part> partsMap;
    private HashMap<PartName, PartName> nextPartMap;
    private PartName currentPart;

    private SoundReaction soundReaction;

    public PartManager(PApplet parent) {
        this.parent = parent;
        partsMap = new HashMap<>();
        nextPartMap = new HashMap<>();
        currentPart = PartName.LINES_DIRTY_WIZARD;
        soundReaction = new SoundReaction(parent, 32);//32
    }

    private void initPartsMap() {
        partsMap.put(PartName.SOUND_REACTION_TEST, new SoundReactionTestPart(parent, soundReaction));
        partsMap.put(PartName.LINES_DIRTY_WIZARD, new LinesDirtyWizard(parent, soundReaction));
        partsMap.put(PartName.WAVES, new WavesPart(parent, soundReaction));
    }

    private void initNextPartMap() {
//        nextPartMap.put(PartName.SOUND_REACTION_TEST, PartName.WAVES);
        nextPartMap.put(PartName.LINES_DIRTY_WIZARD, PartName.LINES_DIRTY_WIZARD);
    }

    private void init() {
        initPartsMap();
        initNextPartMap();
    }

    public Part currentPart() {
        return partsMap.get(currentPart);
    }

    public void setCurrentPart(PartName partName) {
        this.currentPart = partName;
    }

    public void start() {
        init();
        currentPart().start();
    }

    public void startNextPart() {
        currentPart = nextPartMap.get(currentPart);
        currentPart().start();
        System.out.println("Started part " + currentPart.name());
    }

    private void nextPartIfCurrentFinished() {
        if (!currentPart().isActive()) {
            startNextPart();
        }
    }

    public void draw() {
        parent.blendMode(PConstants.SCREEN);
        soundReaction.update();
        parent.background(0);
        parent.pushMatrix();
        parent.pushStyle();
        currentPart().draw();
        parent.popStyle();
        parent.popMatrix();
        nextPartIfCurrentFinished();
    }

    public void managePartsOnKey(char key) {
        switch (key) {
            case ' ': {
                currentPart().finish();
                startNextPart();
            }
        }
    }

    public void controllerChange(int channel, int number, int value) {
        soundReaction.controllerChange(channel, number, value);
        currentPart().controllerChange(channel, number, value);
    }

    public void noteOn(int channel, int pitch, int velocity) {
        currentPart().noteOn(channel, pitch, velocity);
    }
    public void noteOff(int channel, int pitch, int velocity) {
        currentPart().noteOff(channel, pitch, velocity);
    }
}

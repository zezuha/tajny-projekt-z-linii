package secretLines.parts.utils.manager;

import processing.core.PApplet;
import secretLines.media.soundReaction.SoundReaction;
import secretLines.midi.MidiReaction;
import secretLines.parts.part.Part;
import secretLines.parts.soundReactionTestPart.SoundReactionTestPart;
import secretLines.parts.utils.name.*;
import secretLines.parts.video3Dizer.Video3Dizer;

import java.util.HashMap;

import static processing.core.PApplet.map;

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
        currentPart = PartName.SOUND_REACTION_TEST;
        soundReaction = new SoundReaction(parent, 32);
    }

    private void initPartsMap() {
        partsMap.put(PartName.SOUND_REACTION_TEST, new SoundReactionTestPart(parent, soundReaction));
        partsMap.put(PartName.VIDEO_3DIZER, new Video3Dizer(parent, soundReaction));
    }

    private void initNextPartMap() {
        nextPartMap.put(PartName.VIDEO_3DIZER, PartName.SOUND_REACTION_TEST);
        nextPartMap.put(PartName.SOUND_REACTION_TEST, PartName.VIDEO_3DIZER);
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
            case 'n': {
                currentPart().finish();
                startNextPart();
            }
        }
    }

    public void controllerChange(int channel, int number, int value) {
        if(currentPart == PartName.VIDEO_3DIZER) {
            Video3Dizer video3Dizer = (Video3Dizer)currentPart();
            video3Dizer.controllerChange(channel, number, value);
        }
    }

    public void noteOn(int channel, int pitch, int velocity) {
        if(currentPart() instanceof MidiReaction) {
            ((MidiReaction) currentPart()).noteOn(channel, pitch, velocity);
        }
    }
    public void noteOff(int channel, int pitch, int velocity) {
        if(currentPart() instanceof MidiReaction) {
            ((MidiReaction) currentPart()).noteOff(channel, pitch, velocity);
        }
    }
}

package secretLines.parts.utils.manager;

import processing.core.PApplet;
import secretLines.media.soundReaction.SoundReaction;
import secretLines.parts.part.Part;
import secretLines.parts.soundReactionTestPart.SoundReactionTestPart;
import secretLines.parts.utils.name.*;

import java.util.HashMap;

public class PartManager {

    private PApplet parent;

    private HashMap<PartName, Part> partsMap;
    private HashMap<PartName, PartName> nextPartMap;
    private PartName currentPart;

    private SoundReaction soundReaction;

    public PartManager(PApplet parent) {
        this.parent = parent;
        partsMap = new HashMap<>();
        nextPartMap = new HashMap<>();
        currentPart = PartName.VIDEO_3DIZER;
        soundReaction = new SoundReaction(parent, 32);
    }

    private void initPartsMap() {
        partsMap.put(PartName.VIDEO_3DIZER, new SoundReactionTestPart(parent, soundReaction));
    }

    private void initNextPartMap() {
        nextPartMap.put(PartName.VIDEO_3DIZER, PartName.VIDEO_3DIZER);
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
        System.out.println("Started parts " + currentPart.name());
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
}

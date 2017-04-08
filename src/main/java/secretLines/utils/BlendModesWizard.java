package secretLines.utils;


import processing.core.PApplet;
import processing.core.PConstants;


public class BlendModesWizard {

    private PApplet parent;
    private int[] blendModes;
    private int blendModeIndex;

    public BlendModesWizard(PApplet parent) {
        this.parent = parent;
        blendModes = new int[] {PConstants.SCREEN}; //, PConstants.LIGHTEST, PConstants.EXCLUSION
        blendModeIndex = 0;
    }

    public int getBlendMode() {
        return blendModes[blendModeIndex];
    }

    public void nextBlendMode() {
        blendModeIndex++;
        if(blendModeIndex >= blendModes.length) { blendModeIndex = 0; }
    }

    public void previousBlendMode() {
        blendModeIndex--;
        if(blendModeIndex < 0) { blendModeIndex = blendModes.length - 1; }
    }

    public void setBlendMode() {
        parent.blendMode(blendModes[blendModeIndex]);
    }
}

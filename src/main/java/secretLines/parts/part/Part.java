package secretLines.parts.part;

import processing.core.PApplet;

public abstract class Part {

    protected PApplet parent;

    protected boolean isActive;

    protected Part() {
        isActive = false;
    }

    protected Part(PApplet parent) {
        this();
        this.parent = parent;
    }

    public boolean isActive() {
        return isActive;
    }

    public void start() {
        isActive = true;
    }

    public void finish() {
        isActive = false;
    }

    public abstract void draw();
}

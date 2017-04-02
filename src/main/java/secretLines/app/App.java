package secretLines.app;

import processing.core.PApplet;
import secretLines.parts.utils.manager.PartManager;
import themidibus.MidiBus;


public class App extends PApplet {

    private PartManager partManager;
    private MidiBus midiBus;

    public void settings() {
        size(1920, 1080, P3D);
    }

    public void setup() {
        partManager = new PartManager(this);
        partManager.start();
        MidiBus.list();
        midiBus = new MidiBus(this, 0, 3);
    }

    public void draw() {
        partManager.draw();
    }

    public void keyPressed() {
        System.out.println("Pressed key: " + keyCode);
        switch (keyCode) {
            case 80: { // p
                saveFrame("data/screenshots/screenshot-" + (int)random(1000) + ".jpg");
            } break;
        }
        partManager.managePartsOnKey(key);
    }

    void noteOn(int channel, int pitch, int velocity) {
        // Receive a noteOn
        println();
        println("Note On:");
        println("--------");
        println("Channel:"+channel);
        println("Pitch:"+pitch);
        println("Velocity:"+velocity);
        partManager.noteOn(channel, pitch, velocity);
    }

    void noteOff(int channel, int pitch, int velocity) {
        // Receive a noteOff
        println();
        println("Note Off:");
        println("--------");
        println("Channel:"+channel);
        println("Pitch:"+pitch);
        println("Velocity:"+velocity);
        partManager.noteOff(channel, pitch, velocity);
    }

    void controllerChange(int channel, int number, int value) {
        // Receive a controllerChange
        println();
        println("Controller Change:");
        println("--------");
        println("Channel:"+channel);
        println("Number:"+number);
        println("Value:"+value);
        partManager.controllerChange(channel, number, value);
    }

    public static void main(String[] args) {
        PApplet.main( App.class.getCanonicalName());
    }
}

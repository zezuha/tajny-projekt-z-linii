package secretLines.app;

import processing.core.PApplet;


public class App extends PApplet {

    public void settings() {
        size(1920, 1080, P3D);
    }

    public void setup() {
    }

    public void draw() {
        background(0);
        fill(255, 0, 0);
        rect(200, 200, 200, 200);
    }

    public void keyPressed() {
        System.out.println("Pressed key: " + keyCode);
        switch (keyCode) {
            case 80: { // p
                saveFrame("data/screenshots/screenshot-" + (int)random(1000) + ".jpg");
            } break;
        }
    }

    public static void main(String[] args) {
        PApplet.main( App.class.getCanonicalName());
    }
}

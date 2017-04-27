package secretLines.parts.urodziny;

import processing.core.PApplet;
import processing.core.PImage;
import secretLines.parts.part.Part;


public class AnnaHelena extends Part {

    private PImage annaImg;
    private PImage helenaImg;
    private PImage milkoImg;


    public AnnaHelena(PApplet parent) {
        super(parent);
        annaImg = parent.loadImage("anna.jpg");
        helenaImg = parent.loadImage("helena.jpg");
        milkoImg = parent.loadImage("milko.jpg");
    }

    public void draw() {

        parent.background(parent.millis() % 255);

        if(parent.keyPressed) {
            switch (parent.key) {
                case 'z': {
                    parent.image(annaImg, 0, 0);
                }
                break;
                case 'x': {
                    parent.image(helenaImg, 0, 0);
                }
                break;
                case 'c': {
                    parent.image(milkoImg, 0, 0);
                }
                break;
            }
        }
    }
}

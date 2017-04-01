//package secretLines.parts.video3Dizer;
//
//import processing.core.PApplet;
//import processing.core.PImage;
//
//import static secretLines.config.ConfigConstants.HUMAN3_SOUND_PATH;
//import static secretLines.config.ConfigConstants.HUMAN3_VIDEO_PATH;
//import static processing.core.PApplet.map;
//import static processing.core.PConstants.*;
//
//public class Video3Dizer extends BasicMoviePart {
//
//    private PImage movieImg;
//
//    private int skip;
//    private float glitchLevel;
//    private final int MAX_Z = 600;
//    private int maxZ;
//
//    public Video3Dizer(PApplet parent) {
//        super(parent, HUMAN3_SOUND_PATH, HUMAN3_VIDEO_PATH); //HUMAN3_SOUND_PATH
//        moviePlayer.setMaxDuration(90);
//        skip = 10;
//    }
//
//    @Override
//    public void start() {
//        super.start();
//    }
//
//    @Override
//    public void finish() {
//        super.finish();
//    }
//
//    private void updateMovie() {
//        moviePlayer.readFrame();
//        movieImg = moviePlayer.getMovie();
//        movieImg.loadPixels();
//    }
//
//    private void drawBlueMesh() {
//        parent.rotateX(-map(maxZ, 0, MAX_Z, 0, PI/12));
//        skip = 10;
//        parent.stroke(0, 0, 255, 255);
//        for (int y = 0; y < movieImg.height - skip; y += skip) {
//            parent.beginShape(TRIANGLE_STRIP);
//            for (int x = 0; x < movieImg.width - skip; x += skip) {
//                int y1 = y + skip;
//                int index = y * movieImg.width + x;
//                int index1 = y1 * movieImg.width + x;
//                float z = map(parent.brightness(movieImg.pixels[index]), 0, 255, 0, maxZ);
//                float z1 = map(parent.brightness(movieImg.pixels[index1]), 0, 255, 0, maxZ);
//                int tx = x;
//                int ty = y;
//                if(parent.random(100) < glitchLevel) {
//                    tx += parent.random(80) - 40;
//                    ty += parent.random(80) - 40;
//                    parent.vertex(x, y, z);
//                    parent.vertex(x, y1, z1);
//
//                }
//                parent.vertex(tx, ty, z);
//                parent.vertex(tx, y1, z1);
//            }
//            parent.endShape();
//        }
//    }
//
//    private void drawGreenMesh() {
//        skip = 15;
//        parent.stroke(0, 255, 0, 255);
//        parent.strokeWeight(3);
//        for (int y = 0; y < movieImg.height - skip; y += skip) {
//
//            for (int x = 0; x < movieImg.width - skip; x += skip) {
//                int x1 = x + skip;
//                int index = y * movieImg.width + x;
//                int index1 = y * movieImg.width + x1;
//                float z = map(parent.brightness(movieImg.pixels[index]), 0, 255, 0, 400);
//                float z1 = map(parent.brightness(movieImg.pixels[index1]), 0, 255, 0, 400);
//                parent.beginShape(LINES);
//                parent.vertex(x, y, z);
//                parent.vertex(x1, y, z1);
//                parent.endShape();
//            }
//        }
//    }
//
//    private void drawBoxes() {
//        skip = 20;
//        maxZ = MAX_Z - maxZ;
//        parent.rotateX(-map(maxZ, 0, MAX_Z, 0, PI/12));
//        parent.stroke(255, 0, 0, 255 - parent.random(100));
//        for (int y = 0; y < movieImg.height - skip; y += skip) {
//            for (int x = 0; x < movieImg.width - skip; x += skip) {
//                int index = y * movieImg.width + x;
//                float z = map(parent.brightness(movieImg.pixels[index]), 0, 255, 0,  maxZ);
//                float boxSize = 40 - map(z, 0, maxZ, 40, 10);
//                parent.pushMatrix();
//                parent.translate(x - boxSize / 2, y - boxSize / 2, z - boxSize / 2);
//                parent.box(boxSize);
//                parent.popMatrix();
//            }
//        }
//    }
//
//    private void drawMeshAndBoxes() {
//        parent.pushStyle();
//        parent.background(0);
//        parent.noFill();
//        parent.translate(700, 250, 300);//400
//
//        parent.pushMatrix();
//        parent.pushStyle();
//        drawGreenMesh();
//        parent.popStyle();
//        parent.popMatrix();
//
//        parent.pushMatrix();
//        drawBlueMesh();
//        parent.popMatrix();
//
//        parent.pushMatrix();
//        drawBoxes();
//        parent.popMatrix();
//
//        parent.popStyle();
//    }
//
//    @Override
//    public void draw() {
//        updateMovie();
//        drawMeshAndBoxes();
//        checkFinish();
//    }
//}
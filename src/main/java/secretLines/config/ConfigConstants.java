package secretLines.config;

import processing.core.PConstants;

public final class ConfigConstants {

    public static final int FPS = 25;

//    public static final String VIDEO_PATH = "video/heaven mandel 2.mov";
    public static final String VIDEO_PATH = "video/gradient5_linie.mp4";
//    public static final String VIDEO_PATH = "video/pattern2.mov";
//    public static final String VIDEO_PATH = "video/gradient_tety.mp4";
    public static final String MUSIC_PATH = "muzyka.mp3";
    public static final String LAYER1_PATH = "video/layers/linie2.5.mp4";
    public static final String LAYER2_PATH = "video/layers/linie3d_linie_geometrycznie_BeamMeUp_normal.mp4";
    public static final String LAYER3_PATH = "video/layers/linie3d_linie_geometrycznie_gwiazda.mp4";
    public static final String LAYER4_PATH = "video/layers/linie3d_linie_geometrycznie_jez.mov";
    public static final String LAYER5_PATH = "video/layers/linie3d_linie_geometrycznie_kreski4.mp4";
    public static final String LAYER6_PATH = "video/layers/linie3d_linie_geometrycznie_obrot2.mp4";
    public static final int[] BLEND_MODES = new int[]{PConstants.SCREEN, PConstants.LIGHTEST, PConstants.EXCLUSION};
    private ConfigConstants() {
        throw new Error();
    }
}

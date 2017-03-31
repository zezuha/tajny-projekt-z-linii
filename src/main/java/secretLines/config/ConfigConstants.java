package secretLines.config;

public final class ConfigConstants {

    public static final int FPS = 25;
    public static final int MAX_VIDEO_DURATION_S = 1200;

    public static final int KINECT_MAX = 1000;
    public static final int KINECT_MIN = 800; //closest

    public static final String SOUNDTRACK_PATH = "soundtrack2.wav";

    public static final String INTRO1_PATH = "intros/1.mov";
    public static final String INTRO2_PATH = "intros/forma.mov";
    public static final String INTRO3_PATH = "intros/3.mov";
    public static final String INTRO4_PATH = "intros/4.mov";
    public static final String INTRO5_PATH = "intros/intro5.mov";
    public static final String INTRO6_PATH = "intros/introSiatka.mov";
    public static final String INTRO1_SOUND_PATH = null;//"intros/intro1.wav";
    public static final String INTRO2_SOUND_PATH = null;//"intros/intro2.wav";
    public static final String INTRO3_SOUND_PATH = null;//"intros/intro3.wav";
    public static final String INTRO4_SOUND_PATH = null;//"intros/intro4.wav";
    public static final String INTRO6_SOUND_PATH = null;//"intros/introSiatka.wav";

    public static final String PENROSE_TRIANGLE_OBJ_PATH = "penrose/KHL_penrose_triangle.obj";
    public static final String PENROSE_BZZ_SOUND_PATH = "penrose/bzzz.wav" ;
    public static final String PENROSE_ANIMATION_PATH = "penrose/klatki_krotki2.mov" ;

    public static final String NATURE_VIDEO_PATH = "nature/las2_muza.mp4";
    public static final String NATURE_SOUND_PATH = null;//"nature/las.mp3";

    public static final String NATURE2_VIDEO_BLUE_PATH = "nature2/niebieska.mp4";
    public static final String NATURE2_VIDEO_CENTER_PATH = "nature2/bezmuzy.mp4"; //center
    public static final String NATURE2_VIDEO_RED_PATH = "nature2/czerwona.mp4";
    public static final String NATURE2_SOUND_PATH = null;//"nature2/klarnet_srarnet.wav";

    public static final String NATURE3_VIDEO_1_PATH = "nature3/1_lisc.mp4";
    public static final String NATURE3_VIDEO_2_PATH = "nature3/liscie3.mp4";
    public static final String NATURE3_SOUND_PATH = null;//"nature3/natura3.wav";

    public static final String HUMAN1_VIDEO_1_PATH = "human1/ziom1.mp4";

    public static final String HUMAN1_VIDEO_2_PATH = "human2/human2_mniejssze.mp4";
    public static final String HUMAN1_VIDEO_3_PATH = "human1/ziom3.mp4";
    public static final String HUMAN1_VIDEO_4_PATH = "human1/ziom4.mp4";
    public static final String HUMAN1_SOUND_ALL_PATH = null;//"human1/all.wav";
    public static final String HUMAN1_SOUND_1_PATH = null;//"human1/1.wav";
    public static final String HUMAN1_SOUND_2_PATH = null;//"human1/2.wav";
    public static final String HUMAN1_SOUND_3_PATH = null;//"human1/3.wav";
    public static final String HUMAN1_SOUND_4_PATH = null;//"human1/4.wav";

    public static final String HUMAN2_VIDEO_COLOR_PATH = "human2/preset_human_2.4.mp4";
    public static final String HUMAN2_VIDEO_BW_PATH = "human2/bw2.mp4";
    public static final String HUMAN2_SOUND_PATH = null;//"human2/human2.mp3";

    public static final String HUMAN3_VIDEO_PATH = "human3/ryjo33.mp4";
    public static final String HUMAN3_SOUND_PATH = null;//"human3/siatka.wav";

    public static final int KINECT_CLOSEST_POINTS_HISTORY_SIZE = 20;

    private ConfigConstants() {
        throw new Error();
    }
}

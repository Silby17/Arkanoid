package animation;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * Created by Yossi Silberhaft and Binyamin Greenberg.
 */
public class AnimationRunner {
    private GUI gui;
    private Sleeper sleeper = new Sleeper();
    private int framesPerSecond;

    /**
     * Run the animation on the GUI.
     *
     * @param gui the gui to be animated.
     * @param framesPerSecond is the frames per second.
     */
    public AnimationRunner(GUI gui, int framesPerSecond) {
        this.framesPerSecond = framesPerSecond;
        this.gui = gui;
    }

    /**
     * Run the Animation.
     *
     * @param animation the animation to be run.
     */
    public void run(Animation animation) {
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d, (double) framesPerSecond / 3600);
            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = 1000 / framesPerSecond - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}

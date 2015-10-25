package animation;
import biuoop.DrawSurface;
import sprite.SpriteCollection;

/**
 * @author Yossi Silberhaft and Binyamin Greenberg.
 *
 * Class that will activate the COuntdown animation.
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private int countDown;
    private boolean stop = false;
    private SpriteCollection gameScreen;
    private double initialTime = System.currentTimeMillis();

    /**
     * Constructor Method.
     *
     * @param numOfSeconds between each animation.
     * @param countFrom a starting number.
     * @param gameScreen that the animation will be displayed on.
     */
    public CountdownAnimation(double numOfSeconds,
                              int countFrom,
                              SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.countDown = countFrom;
    }

    /**
     * Does the animation at each frame.
     *
     * @param d what to draw.
     * @param dt the frames per second.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        this.gameScreen.drawAllOn(d);
        this.gameScreen.drawAllOn(d);
        d.setColor(java.awt.Color.RED);
        d.drawText(390, 400, getNumber(), 45);
        if (countDown < 1) {
            stop = true;
        }
    }

    /**
     * Get the number of the count down.
     *
     * @return the number.
     */
    private String getNumber() {
        double currentTime = System.currentTimeMillis();
        if ((currentTime - initialTime)
                > (double) (numOfSeconds / (countFrom + 1)) * 1000) {
            countDown--;
            initialTime = System.currentTimeMillis();
        }

        return ("" + countDown + "");
    }

    /**
     * Checks if the animation should stop or not.
     *
     * @return true or false.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}
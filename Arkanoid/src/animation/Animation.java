package animation;
import biuoop.DrawSurface;

/**
 * Created by Yossi Silberhaft and Binyamin Greenberg.
 */
public abstract interface Animation {
    /**
     * Draws the objects to the surface.
     *
     * @param d The surface to be drawn.
     * @param dt the frames per second.
     */
    void doOneFrame(DrawSurface d, double dt);

    /**
     * Boolean whether to stop or not.
     *
     * @return true or false.
     */
    boolean shouldStop();
}

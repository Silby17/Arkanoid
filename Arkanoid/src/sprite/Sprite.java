package sprite;
import biuoop.DrawSurface;
/**
 * This class will create the Sprite interface.
 * @author Yossi Silberhaft and Binyamin Greeberg.
 *
 */
public interface Sprite {
    /**
     * This will call the draw Method for drawing
     * the sprite to the screen.
     * @param d the sprite to be drawn.
     */
    void drawOn(DrawSurface d);
    /**
     * This will call the method for drawing
     * the sprite borders to the screen.
     * @param d the Sprite to be drawn.
     */
    void drawBorder(DrawSurface d);
    /**
     * Notify the Sprite that time has passed.
     * @param dt frames per second.
     */
    void timePassed(double dt);
}
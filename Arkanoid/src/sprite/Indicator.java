package sprite;
import animation.GameLevel;
import arkanoid.Counter;
import biuoop.DrawSurface;
import geometry.Rectangle;

import java.awt.Color;

/**
 * Created by Yossi Silberhaft and Binyamin Greenberg.
 */
public class Indicator implements Sprite {
    private Rectangle rectangle;
    private Color color = Color.LIGHT_GRAY;
    private Counter counter;
    private String name;



    /**
     * Constructor method.
     *
     * @param r Rectangle shape to be displayed.
     */
    public Indicator(Rectangle r) {
        this.rectangle = r;
    }

    /**
     * Gets the name of the Level.
     *
     * @return the name of the level.
     */
    public String getName() {
        return name;
    }

    /**
     * Counter to keep count for the indicator.
     *
     * @return the Counter.
     */
    public Counter getCounter() {
        return counter;
    }
    /**
     * Receive the Rectangle.
     *
     * @return the current Rectangle.
     */
    public Rectangle getRectangle() {
        return rectangle;
    }

    /**
     * Method do draw on the GUI.
     *
     * @param d the sprite to be drawn.
     */

    public void drawOn(DrawSurface d) { };

    /**
     * Method to draw a border.
     *
     * @param d the Sprite to be drawn.
     */
    @Override
    public void drawBorder(DrawSurface d) {

    }

    /**
     * Method that will monitor the time passed.
     * @param dt frames per second.
     */
    @Override
    public void timePassed(double dt) {

    }

    /**
     * Method that will add the indicator to the game.
     *
     * @param g The game to be added to.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}

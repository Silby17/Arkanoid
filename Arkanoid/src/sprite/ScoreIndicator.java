package sprite;
import arkanoid.Counter;
import biuoop.DrawSurface;
import geometry.Rectangle;

import java.awt.Color;

/**
 * Created by Yossi Silberhaft and Binyamin Greenberg.
 */
public class ScoreIndicator extends Indicator {
    private Counter scoreCounter;
    /**
     * Score indicator from the Parent class.
     *
     * @param r Rectangle in the parent Class.
     * @param c Counter in the Parent Class.
     */
    public ScoreIndicator(Rectangle r, Counter c) {
        super(r);
        scoreCounter = c;
    }

    /**
     * Method that will Draw the Drawings onto the Gui.
     *
     * @param d the sprite to be drawn.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.LIGHT_GRAY);
        d.fillRectangle((int) this.getRectangle().getUpperLeft().getX(),
                (int) this.getRectangle().getUpperLeft().getY(),
                (int) this.getRectangle().getWidth(),
                (int) this.getRectangle().getHeight());

        d.setColor(Color.BLACK);
        int midRecX = (int) (this.getRectangle().
                getUpperLeft().getX()
                + this.getRectangle().getWidth() / 2 - 5);
        int midRecY = (int) (this.getRectangle().
                getUpperLeft().getY()
                + this.getRectangle().
                getHeight() / 2 + 5);

        d.drawText(midRecX, midRecY,
                "Score: " + scoreCounter.getValue() + "", 15);
    }
}

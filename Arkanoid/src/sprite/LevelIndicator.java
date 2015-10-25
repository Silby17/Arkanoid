package sprite;
import biuoop.DrawSurface;
import geometry.Rectangle;

import java.awt.Color;

/**
 * Created by Yossi Silberhaft and Binyamin Greenberg.
 */
public class LevelIndicator extends Indicator {
    private String name;

    /**
     * Constructor method.
     *
     * @param r Rectangle shape to be displayed.
     * @param s the string in the Indicator.
     */
    public LevelIndicator(Rectangle r, String s) {
        super(r);
        name = s;
    }

    /**
     * Method that will draw the surface onto the Gui.
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

        d.drawText((int) this.getRectangle().getUpperLeft().getX() + 10,
                midRecY, "Level: " + name, 15);

        //d.drawText(midRecX, midRecY, "Level: " + name, 15);
    }
}

package sprite;
import arkanoid.Counter;
import biuoop.DrawSurface;
import geometry.Rectangle;

import java.awt.Color;

/**
 * Created by Yossi Silberhaft and Binyamin Greenberg.
 */
public class LivesIndicator extends Indicator {
    private Counter lives;
    /**
     * Lives Indicator from the Parent Class.
     *
     * @param r Rectangle from Parent Class.
     * @param c Counter from the parent class.
     */
    public LivesIndicator(Rectangle r, Counter c) {
        super(r);
        lives = c;
    }

    @Override
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

        d.drawText(midRecX, midRecY, "Lives: " + lives.getValue() + "", 15);
    }
}

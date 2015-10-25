package sprite;

import biuoop.DrawSurface;
import geometry.Rectangle;
/**
 * Created by Yossi Silberhaft and Binyamin Greenberg.
 */
public class Borders extends Block {
    /**
     * The Constructor for the block.
     *
     * @param r receives the rectangle.
     * @param c receives the color for the block.
     * @param n is the counter of the hits.
     */
    public Borders(Rectangle r, String c, int n) {
        super(r, c, n, null, null);
    }

    /**Sends the parameters to have the borders drawn.
     *
     * @param d is the object received that will have its
     * border drawn.
     */
    @Override
    public void drawBorder(DrawSurface d) {

    }
}

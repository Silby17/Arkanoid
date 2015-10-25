package levels;
import geometry.Point;
import geometry.Rectangle;
import sprite.Block;
import java.awt.Color;
import java.util.HashMap;

/**
 * Created by Yossi Silberhaft and Binyamin Greenberg.
 */
public class BlockCreatorObject implements BlockCreator {
    private int height;
    private int width;
    private int hits;
    private String fill;
    private Color strokeColor;
    private HashMap<Integer, String> hashFill;

    /**
     * Constructor method.
     *
     * @param height the height.
     * @param width the width.
     * @param hits the amount of hit points.
     * @param fill what to fill.
     * @param s the Color.
     * @param h the HashMap.
     */
    public BlockCreatorObject(int height, int width, int hits, String fill,
                              Color s, HashMap<Integer, String> h) {
        this.height = height;
        this.width = width;
        this.hits = hits;
        this.fill = fill;
        this.strokeColor = s;
        this.hashFill = h;
    }

    /**
     * Method that will create the blocks.
     *
     * @param xpos the x - coordinate.
     * @param ypos the y - coordinate.
     * @return the block.
     */

    public Block create(int xpos, int ypos) {
        Block block = new Block(new Rectangle(new Point(xpos, ypos),
                width, height), fill, hits, strokeColor, hashFill);
        return block;
    }
}
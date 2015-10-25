package levels;
import sprite.Block;

/**
 * Created by Yossi Silberhaft and Binyamin Greenberg.
 */
public interface BlockCreator {
    /**
     * Create a block at the Specific location.
     *
     * @param xpos x coordinate.
     * @param ypos y coordinate.
     * @return the block.
     */
    Block create(int xpos, int ypos);
}

package listener;

import animation.GameLevel;
import arkanoid.Counter;
import sprite.Ball;
import sprite.Block;

/**
 * Created by Yossi Silberhaft and Binyamin Greenberg.
 */
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter removedBlocks;

    /**
     * Constructor Method.
     *
     * @param gameLevel is the Game.
     * @param removedBlocks Counter to remove Blocks.
     */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.removedBlocks = removedBlocks;
    }

    // Blocks that are hit and reach 0 hit-points should be removed
    // from the game. Remember to remove this listener from the block
    // that is being removed from the game.

    /**
     * Method that will notify on Hit Events.
     *
     * @param beingHit is the object that is being hit.
     * @param hitter is the object that is doing the Hitting.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHitPoints() == 0) {
            beingHit.removeFromGame(gameLevel);
            removedBlocks.decrease(1);
        }

    }
}

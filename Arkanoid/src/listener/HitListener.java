package listener;

import sprite.Ball;
import sprite.Block;

/**
 * Interface for all the HitListener.
 * This class will listen and notify of any hitting.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * @param beingHit the object being hit.
     * @param hitter the ball that's doing the hitting.
     */
    void hitEvent(Block beingHit, Ball hitter);
}

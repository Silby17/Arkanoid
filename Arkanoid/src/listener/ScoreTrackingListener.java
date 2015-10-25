package listener;

import arkanoid.Counter;
import sprite.Ball;
import sprite.Block;

/**
 * Created by Yossi Silberhaft on 09/05/2014.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * Listener for tracking the scores during the game.
     *
     * @param scoreCounter the counter that will keep the score.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * Method that will notify at the event of an object being hit.
     *
     * @param beingHit the object being hit.
     * @param hitter the ball that's doing the hitting.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHitPoints() > 1) {
            currentScore.increase(10);
        } else {
            currentScore.increase(50);
        }
    }
}

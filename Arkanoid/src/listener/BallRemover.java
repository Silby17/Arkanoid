package listener;
import animation.GameLevel;
import arkanoid.Counter;
import sprite.Ball;
import sprite.Block;

/**
 * Created by Yossi Silberhaft and Binyamin Greenberg.
 */
public class BallRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter removedBalls;

    /**
     * Constructor Method for the Ball remover.
     *
     * @param gameLevel the current game.
     * @param removedBalls the Ball counter.
     */
    public BallRemover(GameLevel gameLevel, Counter removedBalls) {
        this.gameLevel = gameLevel;
        this.removedBalls = removedBalls;

    }

    /**
     * Check to see if an object gets it.
     *
     * @param beingHit a block object being hit.
     * @param hitter A ball doing the hitting.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(gameLevel);
        removedBalls.decrease(1);
    }
}

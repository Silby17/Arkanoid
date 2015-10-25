package levels;
import geometry.Velocity;
import sprite.Block;
import sprite.Sprite;

import java.util.List;

/**
 * Created by Yossi Silberhaft and Binyamin Greenberg.
 */
public interface LevelInformation {
    /**
     * Number of balls.
     *
     * @return the number of balls.
     */
    int numberOfBalls();

    /**
     * The initial velocity of each ball.
     *
     * @return The initial velocity of each ball.
     */
    List<Velocity> initialBallVelocities();

    /**
     * The Speed of the Paddle movement.
     *
     * @return Paddle Speed.
     */
    int paddleSpeed();

    /**
     * The width of the Paddle.
     *
     * @return the width of the Paddle.
     */
    int paddleWidth();


    /**
     * The level name will be displayed at the top of the screen.
     *
     * @return name of the Current Level.
     */
    String levelName();

    /**
     * Returns a sprite with the background of the level.
     *
     * @return Returns a sprite with the background of the level.
     */
    Sprite getBackground();

    /**
     * The Blocks that make up this level, each block contains
     * its size, color and location.
     *
     * @return the list of blocks.
     */
    List<Block> blocks();

    // This number should be <= blocks.size();

    /**
     *  Number of levels that should be removed before
     *  the level is considered to be "cleared".
     *
     * @return number of blocks to be removed.
     */
    int numberOfBlocksToRemove();
}

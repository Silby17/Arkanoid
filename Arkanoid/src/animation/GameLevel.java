package animation;
import arkanoid.Counter;
import arkanoid.GameEnvironment;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import levels.LevelInformation;
import listener.BallRemover;
import listener.BlockRemover;
import listener.ScoreTrackingListener;
import sprite.LevelIndicator;
import sprite.SpriteCollection;
import sprite.Sprite;
import sprite.ScoreIndicator;
import sprite.LivesIndicator;
import sprite.Block;
import sprite.Borders;
import sprite.Ball;
import sprite.Collidable;
import sprite.Paddle;

import java.util.List;

/**
 * @author Yossi Silberhaft and Binyamin Greenberg.
 *
 * This class will create the GameLevels Object.
 */
public class GameLevel implements Animation {
    private LevelInformation levelInfo;
    private boolean running;
    private SpriteCollection sprites = new SpriteCollection();
    private GameEnvironment environment = new GameEnvironment();
    private GUI gui;
    private AnimationRunner runner;
    private static double speed = 100;
    private Counter blockCounter;
    private Counter ballCounter;
    private Counter numberOfLives;
    private Counter scoreCounter;
    private KeyboardSensor keyboard;

    /**
     * The constructor for the Game.
     *
     * @param levelInfo the Level information needed.
     * @param ar the Animation runner.
     * @param gui the gui of the game Level.
     * @param key the Keyboard for the user.
     * @param lives the number of lives.
     * @param score the Score value.
     */
    public GameLevel(LevelInformation levelInfo,
                     AnimationRunner ar, KeyboardSensor key,
                     GUI gui, Counter lives, Counter score) {
        runner = ar;
        keyboard = key;
        this.levelInfo = levelInfo;
        ballCounter = new Counter(0);
        blockCounter = new Counter(levelInfo.numberOfBlocksToRemove());
        this.numberOfLives = lives;
        this.scoreCounter = score;
        this.gui = gui;
    }

    /**
     * Gets the remaining block count.
     *
     * @return the amount of remaining blocks.
     */
    public int getRemainingBlocks() {
        return blockCounter.getValue();
    }

    /**
     * Gets the number of lives.
     *
     * @return the number of lives remaining.
     */
    public int getLives() {
        return numberOfLives.getValue();
    }

    /**
     * Get Speed.
     *
     * @return the speed.
     */
    public static double getSpeed() {
        return speed;
    }
    /**
     * Adds the Sprites.
     *
     * @param s is the Sprite that received.
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * Method that will draw the surface at each frame.
     *
     * @param d object that needs to be drawn.
     * @param dt the framse per second.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed(dt);
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(keyboard,
                    KeyboardSensor.SPACE_KEY, new PauseScreen()));
        }

        /**
         * Will constantly check to see if there still remain
         * either blocks or balls and exit if one of them
         * is zero.
         */
        if ((blockCounter.getValue() == 0)
                || (ballCounter.getValue() == 0)) {
            this.running = false;
        }
    }
    /**
     * This method will add the environment to the Collidable list.
     * @param c is the Collidable received.
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle)
     * and add them to the game.
     */
    public void initialize() {
        sprites.addSprite(levelInfo.getBackground());
        BlockRemover remover = new BlockRemover(this, blockCounter);
        BallRemover ballRemover = new BallRemover(this, ballCounter);
        ScoreTrackingListener points = new ScoreTrackingListener(scoreCounter);
        List<Block> blockList = levelInfo.blocks();
        for (int i = 0; i <  blockList.size(); i++) {
            Block tempBlock = blockList.get(i);
            tempBlock.addToGame(this);
            tempBlock.addHitListener(remover);
            tempBlock.addHitListener(points);
        }


        LivesIndicator livesIndi = new LivesIndicator(new Rectangle(
                new Point(0, 0), 268, 15), numberOfLives);

        ScoreIndicator scoreIndi = new ScoreIndicator(new
                Rectangle(new Point(268, 0), 268, 15), scoreCounter);

        LevelIndicator levelIndi = new LevelIndicator(new
                Rectangle(new Point(532, 0), 268, 15), levelInfo.levelName());

        levelIndi.addToGame(this);
        scoreIndi.addToGame(this);
        livesIndi.addToGame(this);

        /**This will create the borders of the screen **/
        Borders topBlock = new Borders(new Rectangle(new Point(0, 15),
                800, 15), "color(gray)", 0);
        Borders bottomBlock = new Borders(new Rectangle(new Point(0, 599),
                785, 15), "color(gray)", 0);
        Borders leftBlock = new Borders(new Rectangle(new Point(0, 15),
                15, 600), "color(gray)", 0);
        Borders rightBlock = new Borders(new Rectangle(new Point(785, 15),
                15, 600), "color(gray)", 0);

        /**This will add all the border blocks to the game.**/
        rightBlock.addToGame(this);
        leftBlock.addToGame(this);
        topBlock.addToGame(this);
        bottomBlock.addToGame(this);

        /**Add the bottom black as the death-region.**/
        bottomBlock.addHitListener(ballRemover);
    }

    /**
     * Boolean that will check when the game must stop.
     *
     * @return true or false.
     */
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * Method that will create the balls on the top of the paddle.
     */
    public void createBallsOnTopOfPaddle() {
        for (int i = 0; i < levelInfo.numberOfBalls(); i++) {
            Ball tempBall = new Ball(new Point(400, 560), 5,
                    java.awt.Color.WHITE, environment);
            tempBall.setVelocity(levelInfo.initialBallVelocities().get(i));
            tempBall.setTrajectoryLine(new Line(tempBall.getCenter(),
                    tempBall.getVelocity().
                            applyToPoint(tempBall.getCenter())));
            tempBall.addToGame(this);
            ballCounter.increase(1);
        }
    }

    /**
     * Method that will run at the start of each turn.
     */
    public void playOneTurn() {
        Paddle paddy = new Paddle(keyboard, new Rectangle(new
                Point((400 - (levelInfo.paddleWidth() / 2)),
                565), levelInfo.paddleWidth(), 20),
                levelInfo.paddleSpeed());
            paddy.addToGame(this);

            this.createBallsOnTopOfPaddle();
            this.running = true;
            this.runner.run(new CountdownAnimation(2.0, 3, sprites));
            this.runner.run(this);
            paddy.removeFromGame(this);
            if (ballCounter.getValue() == 0) {
            numberOfLives.decrease(1);
        }
        if (blockCounter.getValue() == 0) {
            scoreCounter.increase(100);
        }
    }

    /**
     * Method that will remove the Collidable.
     *
     * @param c the Collidable to be removed.
     */
    public void removeCollidable(Collidable c) {
        environment.removeColliadable(c);
    }

    /**
     * Method to remove a Sprite.
     *
     * @param s the Sprite to be removed.
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }
}
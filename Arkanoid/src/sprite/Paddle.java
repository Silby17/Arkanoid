package sprite;

import animation.GameLevel;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import geometry.Velocity;

import java.awt.Color;

/**
 * This class will create the Paddle object.
 * @author Yossi Silberhaft and Binyamin Greenberg.
 *
 */
public class Paddle implements Collidable, Sprite {
    private Rectangle rectangle;
    private KeyboardSensor key;
    private int speed = 45;

    /**
     * Constructor of the Paddle.
     * @param keyboard will receive the keyboard movements
     * from the user.
     * @param r will be used to build the rectangle
     * which is the paddle.
     * @param speed the speed of the paddle.
     */
    public Paddle(KeyboardSensor keyboard, Rectangle r, int speed) {
        this.rectangle = r;
        this.speed = speed;
        this.key = keyboard;
    }

    /**
     * This method will move the paddle to the left.
     *
     * @param dt frames per second.
     */
    public void moveLeft(double dt) {
        rectangle = new Rectangle(new Point(rectangle.
                getUpperLeft().getX() - speed * dt,
                rectangle.getUpperLeft().getY()),
                rectangle.getWidth(), rectangle.getHeight());
    }
    /**
     * This method will move the paddle to the right.
     *
     * @param dt the frames per second.
     */
    public void moveRight(double dt) {
        rectangle = new Rectangle(new Point(rectangle.
                getUpperLeft().getX() + speed * dt,
                rectangle.getUpperLeft().getY()),
                rectangle.getWidth(), rectangle.getHeight());
    }

    /**
     * This method will draw the paddle to the screen.
     *
     * @param d will send the required info to the Draw method.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(new Color(238, 209, 54));
        d.fillRectangle((int) this.getCollisionRectangle().
                getUpperLeft().getX(),
                (int) this.getCollisionRectangle().getUpperLeft().getY(),
                (int) this.getCollisionRectangle().getWidth(),
                (int) this.getCollisionRectangle().getHeight());
    }
    /**
     * This method will draw the border of the paddle.
     * @param d will send the required info to the Draw method.
     */
    public void drawBorder(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawRectangle((int) this.getCollisionRectangle().
                getUpperLeft().getX(),
                (int) this.getCollisionRectangle().getUpperLeft().getY(),
                (int) this.getCollisionRectangle().getWidth(),
                (int) this.getCollisionRectangle().getHeight());
    }
    /**
     * This will continuously check to see when a left or right
     * arrow is pressed by the user.
     *
     * @param dt frames per second.
     */
    public void timePassed(double dt) {
        if (key.isPressed(KeyboardSensor.RIGHT_KEY)) {
            if (this.getCollisionRectangle().
                    getUpperLeft().getX()
                    + this.getCollisionRectangle().getWidth() <= 785) {
                moveRight(dt);
            }
        }
        if (key.isPressed(KeyboardSensor.LEFT_KEY)) {
            if (this.getCollisionRectangle().
                    getUpperLeft().getX() >= 15) {

                moveLeft(dt);
            }
        }
    }
    /**
     * @return this triangle.
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * This Method will check the Hits of the Paddle.
     * @param p is the Point received by the method.
     * @return true if the Ball hits the point.
     */
    private boolean checkSideHit(Point p) {
        if ((Line.checkPointOnLine(getCollisionRectangle().getTop(), p))
                || (Line.checkPointOnLine(
                        getCollisionRectangle().
                        getBottom(), p))) {
            return false;
        }
        return true;
    }
    /**
     * This method will change the velocity of the ball
     * depending on where on the paddle the ball hits.
     *
     * @param collisionPoint will receive the point of the
     * the collidable to be checked.
     *
     * @param currentVelocity will receive the current
     * velocity to be checked.
     * @param hitter the object doing the hitting.
     * @return the new velocity.
     */
    public Velocity hit(Ball hitter, Point collisionPoint,
                        Velocity currentVelocity) {
        double fiveParts = getCollisionRectangle().getWidth() / 5;
        double upperLeftX = getCollisionRectangle().
                getUpperLeft().getX();
        double colliPoint = collisionPoint.getX();

        double recalcSpeed =  Math.sqrt(Math.pow(currentVelocity.getDx(), 2)
                + Math.pow(currentVelocity.getDy(), 2));

        if (colliPoint < upperLeftX + fiveParts) {
            return new Velocity(Velocity.
                    fromAngleAndSpeed(300, recalcSpeed));

        } else if (colliPoint < upperLeftX + fiveParts * 2) {
            return new Velocity(Velocity.
                    fromAngleAndSpeed(330, recalcSpeed));

        } else if (colliPoint < upperLeftX + fiveParts * 3) {
            return new Velocity(currentVelocity.getDx() * 1,
                    currentVelocity.getDy() * -1);

        } else if (colliPoint < upperLeftX + fiveParts * 4) {
            return new Velocity(Velocity.
                    fromAngleAndSpeed(30, recalcSpeed));

        } else {
            return new Velocity(Velocity.
                    fromAngleAndSpeed(60, recalcSpeed));
        }
    }
    /**
     * Will add the paddle to Collidable and Sprite.
     * @param g is the game that will receive the paddle.
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * Method that will remove the paddle from the game.
     *
     * @param g the paddle to be removed.
     */
    public void removeFromGame(GameLevel g) {
        g.removeCollidable(this);
        g.removeSprite(this);
    }
}
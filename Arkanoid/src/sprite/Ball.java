package sprite;
import animation.GameLevel;
import arkanoid.GameEnvironment;
import geometry.Point;
import geometry.Line;
import biuoop.DrawSurface;
import geometry.Velocity;

/**
 * This Class will create the object Ball.
 *
 * @author Yossi Silberhaft and Binyamin Greenberg.
 */

public class Ball implements Sprite {
    private Point center;
    private int r;
    private java.awt.Color colour;
    private Velocity velocity;
    private Line trajectoryLine;
    private GameEnvironment game;

    /**
     * This constructor will receive all the necessary figures
     * to create a ball.
     *
     * @param center is the value of the center of the ball.
     * @param r is the value of the radius of the ball.
     * @param color is the color of the ball.
     * @param g is the game environment.
     */
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment g) {
        this.center = center;
        this.r = r;
        colour = color;
        game = g;
    }
    /**
     * This constructor will receive all the values and place
     * them into the constructors.
     *
     * @param x is the value of the X coordinate.
     * @param y is the value of the Y coordinate.
     * @param r is the radius of the ball.
     * @param color is the color of the ball.
     */
    public Ball(int x, int y, int r, java.awt.Color color) {
        center = new Point(x, y);
        this.r = r;
        colour = color;
    }

    /**
     * Method that will get the X value.
     * @return the X value.
     */
    public int getX() {
        return (int) center.getX();
    }

    /**
     * Method that will get the Y value.
     * @return the Y value.
     */
    public int getY() {
        return (int) center.getY();
    }
    /**
     * Method that will get the size of the Ball.
     * @return the radius of the ball.
     */
    public int getSize() {
        return r;
    }
    /**
     * This Method will get the color from the constructor.
     * @return the Color.
     */
    public java.awt.Color getColor() {
        return colour;
    }
    /**
     * This method will Draw the ball on the given DrawSurface.
     * @param surface is the value of the surface area.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(colour);
        surface.fillCircle(this.getX(), this.getY(), this.r);
    }
    /**
     * This method will draw the borders of the ball.
     * @param d is the object that has to have its
     * borders drawn.
     */
    public void drawBorder(DrawSurface d) {
      d.setColor(java.awt.Color.BLACK);
      d.drawCircle(this.getX(), this.getY(), this.r);
    }

    /**
     * This will set the Velocity of the Ball.
     * @param i is the dx of the velocity.
     * @param j is the dy of the Velocity.
     */
    public void setVelocity(int i, int j) {
        this.velocity = new Velocity(i, j);
        this.trajectoryLine = new Line(center, new Point(velocity.
                getDx() * 1000, velocity.getDy() * 1000));
    }
    /**
     * @return the trajectory line of the ball
     */
    public Line getTrajectoryLine() {
        return trajectoryLine;
    }
    /**
     * @return the center of the line.
     */
    public Point getCenter() {
        return center;
    }
    /**
     * @param l the Trajectory of line l.
     */
    public void setTrajectoryLine(Line l) {
        trajectoryLine = l;
    }
    /**
     * This will set the Velocity of the Ball.
     * @param v an object Velocity.
     */
    public void setVelocity(Velocity v) {
        velocity = v;
    }

    /**
     * @return the Velocity.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * This method will move the ball around the screen
     * and make sure that when it collides with any collidable
     * the ball will move in the correct way.
     *
     * @param dt is the frames per second.
     */
    public void moveOneStep(double dt) {
        Velocity scaledVelocity = this.velocity.scale(dt);
        setTrajectoryLine(new Line(center,
                scaledVelocity.applyToPoint(center)));

        if (this.game.getClosestCollision(trajectoryLine) != null) {
            Point collisionPoint = this.game.
                    getClosestCollision(trajectoryLine).getPoint();
            Collidable collidableObject = this.game.
                    getClosestCollision(trajectoryLine).
                    getCollidableObeject();

            center = center.midpoint(collisionPoint);
            setVelocity(collidableObject.hit(this, collisionPoint, velocity));

        } else {
            center = trajectoryLine.end();
        }
    }
    /**
     * This will make the balls bounce and make sure
     * the balls do not leave the borders of the screen.
     *
     * @param dt is the fames per second.
     */
    public void timePassed(double dt) {
        this.moveOneStep(dt);
    }
    /**
     * This will add the sprite to the game.
     * @param g the game to add the Ball to.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * Method that will remove the ball from the game.
     *
     * @param gameLevel the game that ball must be removed from.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
    }


}
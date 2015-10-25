package geometry;
/**
 * This Class will create the object Velocity which will
 * be used to move the object Ball.
 *
 * @author Yossi Silberhaft and Binyamin Greenberg.
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * Constructor for calculating velocity.
     *
     * @param dx is the x value of Velocity.
     * @param dy is the y value of Velocity.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Another constructor for velocity.
     *
     * @param v velocity value received by the method.
     */
    public Velocity(Velocity v) {
        this.dx = v.getDx();
        this.dy = v.getDy();
    }

    /**
     * @return the X value of Velocity.
     */
    public int getDx() {
        return (int) dx;
    }

    /**
     * @return the Y value of Velocity.
     */
    public int getDy() {
        return (int) dy;
    }

    /**
     * Method that will receive a point (x, y) and return a new
     * point with (x+dx, x+dy).
     *
     * @param p is the point.
     * @return the new Point with dx and dy.
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }

    /**
     * This method will convert an angle and speed to
     * dx and dy.
     *
     * @param angle is the angle that will be received
     *              by the method.
     * @param speed is the speed that will be received
     *              by the method.
     * @return the new velocity.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double originalAngle;
        if (angle <= 90) {
            originalAngle = 90 - angle;
        } else {
            originalAngle = 450 - angle;
        }
        double dy = -(Math.sin(Math.toRadians(originalAngle)) * speed);
        double dx = Math.cos(Math.toRadians(originalAngle)) * speed;
        return new Velocity(dx, dy);
    }

    /**
     * Method to scale the velocity.
     *
     * @param dt the frames per second.
     * @return the new velocity.
     */
    public Velocity scale(double dt) {
        return new Velocity(dx * dt, dy * dt);
    }
}
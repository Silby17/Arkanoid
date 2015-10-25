package sprite;

import geometry.Point;

/**
 * This Class will be a referance for all the
 * collisions.
 * @author Yossi Silberhaft and Binyamin Greenberg.
 *
 */
public class CollisionInfo {
    private Point point;
    private Collidable collidableCobject;

    /**
     * the point at which the collision occurs.
     * @param c will receive the collidable.
     */
    public void setCollidableObject(Collidable c) {
        this.collidableCobject = c;
    }
    /**
     * This method will set the point.
     * @param p the point to be set.
     */
    public void setpoint(Point p) {
        this.point = p;
    }

    /**
     * This will get the Point for Collision.
     * @return the point to check collision.
     */
    public Point getPoint() {
        return point;
    }
    /**
     * Receives the collidable object.
     * @return the collidabel Object.
     */
    public Collidable getCollidableObeject() {
        return collidableCobject;
    }

}

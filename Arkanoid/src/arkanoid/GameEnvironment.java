package arkanoid;
import sprite.Collidable;
import sprite.CollisionInfo;
import geometry.Line;
import geometry.Point;
import java.util.ArrayList;
/**
 * This class will create the Game Environment.
 * @author Yossi Silberhaft and Binyamin Greenberg.
 */
public class GameEnvironment {
    /*Create an Array List of Collidables.*/
    private  ArrayList<Collidable> listOfCollidables
    = new ArrayList<Collidable>();

    /**
     * Add the Current collidable to the array list.
     * @param c the current collidable.
     */
    public void addCollidable(Collidable c) {
        listOfCollidables.add(c);
    }

    /**
     * Method that will remove a Collidable from the Environment.
     *
     * @param c the Collidable to be removed.
     */
    public void removeColliadable(Collidable c) {
        listOfCollidables.remove(c);
    }

    /**
     * This method will check for the closest Collision.
     * If this object will not collide with any of the collidables
     * it will return null. Else, return the information
     * about the closest collision that is going to occur.
     * @param trajectory the trajectory to be checked against.
     * @return collision info.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {

        CollisionInfo info = new CollisionInfo();
        Point prevPoint = null;

        for (int i = 0; i < listOfCollidables.size(); i++) {
            if (trajectory.closestIntersectionToStartOfLine(
                    listOfCollidables.get(i).
                    getCollisionRectangle()) != null) {

                 if (prevPoint == null) {
                        prevPoint = trajectory.
                                closestIntersectionToStartOfLine(
                                        listOfCollidables.
                                        get(i).getCollisionRectangle());

                        info.setCollidableObject(
                                listOfCollidables.get(i));
                        info.setpoint(prevPoint);
                    } else if (trajectory.start().
                            distance(trajectory.
                                    closestIntersectionToStartOfLine(
                                            listOfCollidables.
                                            get(i).
                                            getCollisionRectangle()))
                                            < trajectory.start().
                                            distance(prevPoint)) {

                        prevPoint = trajectory.
                                closestIntersectionToStartOfLine(
                                        listOfCollidables.get(i).
                                        getCollisionRectangle());

                        info.setCollidableObject(listOfCollidables.get(i));
                        info.setpoint(prevPoint);
                    }

                }
            }
        if (info.getPoint() == null) {
            return null;
        }
        return info;
    }
}
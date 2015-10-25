package sprite;
import biuoop.DrawSurface;
import java.util.ArrayList;
/**
 * This class will create the Sprite Collection object.
 * @author Yossi Silberhaft
 */
public class SpriteCollection {

    /*Create an array list of sprites.*/
    private ArrayList<Sprite> listOfSprite = new ArrayList<Sprite>();
    /**
     * Add sprite to the Array List.
     * @param s the sprite to add to the list.
     */
    public void addSprite(Sprite s) {
        listOfSprite.add(s);
    }

    /**
     * Method that will remove a Sprite.
     *
     * @param s the Sprite to be removed.
     */
    public void removeSprite(Sprite s) {
        listOfSprite.remove(s);
    }

    /**
     * Call the timePassed method on all the Sprites.
     * @param dt frames per second.
     */
    public void notifyAllTimePassed(double dt) {
        for (int i = 0; i < listOfSprite.size(); i++) {
            listOfSprite.get(i).timePassed(dt);
            }
        }
    /**
     * Calls the drawOn method for all the sprites.
     * @param d the sprites to be draw to the screen.
     */
    public void drawAllOn(DrawSurface d) {
           for (int i = 0; i < listOfSprite.size(); i++) {
               listOfSprite.get(i).drawOn(d);
               listOfSprite.get(i).drawBorder(d);
           }
       }
}

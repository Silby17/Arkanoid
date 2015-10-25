package sprite;
import animation.GameLevel;
import geometry.Rectangle;
import geometry.Point;
import geometry.Line;
import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.imageio.ImageIO;
import biuoop.DrawSurface;
import geometry.Velocity;
import levels.ColorsParser;
import listener.HitListener;
import listener.HitNotifier;

/**
 * This class will create the object Block.
 * @author Yossi Silberhaft and Binyamin Greenberg.
 *
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rectangle;
    private String colorOrImageString;
    private Color strokeColor;
    private int numberOfHits;
    private List<HitListener> hitListeners;
    private HashMap<Integer, String> hashFill;
    private HashMap<Integer, Image> hashImage = new HashMap<Integer, Image>();
    private HashMap<Integer, Color> hashColor = new HashMap<Integer, Color>();

    private Image defaultImage = null;
    private Color defaultColor = null;
    /**
     * The Constructor for the block.
     * @param r receives the rectangle.
     * @param c receives the color for the block.
     * @param n is the counter of the hits.
     * @param s The color.
     * @param h The HashMap.
     */
    public Block(Rectangle r, String c, int n, Color s,
                 HashMap<Integer, String> h) {
        hashFill = h;
        strokeColor = s;
        rectangle = r;
        colorOrImageString = c;
        numberOfHits = n;
        this.hitListeners = new ArrayList<HitListener>();
        intializeHashMaps();
        initalizeDefaultColor();
    }

    /**
     * @return the rectangle.
     */
    public Rectangle getCollisionRectangle() {
        return rectangle;
    }

    /**
     *
     * @return the new velocity.
     *
     * @param collisionPoint is the point of Collision.
     * @param currentVelocity is the current Velocity.
     * @param hitter is the object doing the Hitting.
     */
    public Velocity hit(Ball hitter, Point collisionPoint,
                        Velocity currentVelocity) {
        numberOfHits--;
        int x, y = 0;
        if (checkSideHit(collisionPoint)) {
            x = -1;
            y = 1;
        } else {
            x = 1;
            y = -1;
        }
        this.notifyHit(hitter);
        return new Velocity(currentVelocity.getDx() * x,
                currentVelocity.getDy() * y);
    }

    /**
     * Checks to see if the side of the block was hit.
     * @param p receives the point.
     * @return either true or false.
     */
    private boolean checkSideHit(Point p) {
        if ((Line.checkPointOnLine(getCollisionRectangle().getTop(), p))
                || (Line.checkPointOnLine(
                getCollisionRectangle()
                        .getBottom(), p))) {
            return false;
        }
        return true;
    }
    /**
     * This method will send the parameters of the block
     * to be drawn.
     * @param d is the object received that will be drawn.
     */
    public void drawOn(DrawSurface d) {
        if (hashFill != null) {
            if (hashImage.containsKey(numberOfHits)) {
                d.drawImage((int) rectangle.getUpperLeft().getX(),
                        (int) rectangle.getUpperLeft().getY(),
                        hashImage.get(numberOfHits));
            } else if (hashFill.containsKey(numberOfHits)) {
                d.setColor(hashColor.get(numberOfHits));
                d.fillRectangle((int) this
                                .getCollisionRectangle().getUpperLeft().getX(),
                        (int) this.getCollisionRectangle().
                                getUpperLeft().getY(),
                        (int) this.getCollisionRectangle().getWidth(),
                        (int) this.getCollisionRectangle().getHeight()
                );
            } else {
                drawDefault(d);
            }
        } else {
            drawDefault(d);
        }
    }


    /**Sends the parameters of the block to have the
     * borders drawn.
     * @param d is the object received that will have its
     * border drawn.
     */
    public void drawBorder(DrawSurface d) {
        if (strokeColor != null) {
            d.setColor(strokeColor);
            d.drawRectangle((int) this
                            .getCollisionRectangle().getUpperLeft().getX(),
                    (int) this.getCollisionRectangle().getUpperLeft().getY(),
                    (int) this.getCollisionRectangle().getWidth(),
                    (int) this.getCollisionRectangle().getHeight());
        }
    }
    /**
     * The timing method.
     * @param dt frames per second.
     */
    public void timePassed(double dt) {
    }

    /**Will add the block to the List of Sprites
     * and the List of Collidables.
     * @param gameLevel is the game that the block will be sent to.
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
        gameLevel.addCollidable(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
    }

    /**
     * Function to Notify of any hitting being Done.
     *
     * @param hitter is the Ball object.
     */
    private void notifyHit(Ball hitter) {
        List<HitListener> listeners = new
                ArrayList<HitListener>(this.hitListeners);
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * Method that will remove the Block from
     * the game.
     *
     * @param gameLevel Level to be removed from.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    };

    /**
     * Method to return the HitPoints.
     *
     * @return the HitPoints.
     */
    public int getHitPoints() {
        return numberOfHits;
    }

    /**
     * Method to receive the width.
     *
     * @return the HitPoints.
     */
    public int getWidth() {
        return (int) rectangle.getWidth();
    }

    /**
     * Draws the Surface to the Gui.
     *
     * @param d the Surface to be drawn.
     */
    private void drawDefault(DrawSurface d) {
        if (defaultImage != null) {
            d.drawImage((int) rectangle.getUpperLeft().getX(),
                    (int) rectangle.getUpperLeft().getY(), defaultImage);
        } else {
            d.setColor(defaultColor);
            d.fillRectangle((int) this
                            .getCollisionRectangle().getUpperLeft().getX(),
                    (int) this.getCollisionRectangle().getUpperLeft().getY(),
                    (int) this.getCollisionRectangle().getWidth(),
                    (int) this.getCollisionRectangle().getHeight()
            );
        }
    }

    /**
     * Method to initialize the Default color.
     */
    private void initalizeDefaultColor() {
        if (colorOrImageString != null) {
            String[] stringsArray = colorOrImageString.split("\\(");
            if (stringsArray[0].equals("image")) {
                Image img = null;
                try {
                    String str = stringsArray[1].substring(0,
                            stringsArray[1].length() - 1);
                    img = ImageIO.read(ClassLoader.getSystemClassLoader().
                            getResourceAsStream(str));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                defaultImage = img;
            } else {
                defaultColor = ColorsParser.
                        colorFromString(colorOrImageString);
            }
        }
    }
    /**
     * Initialize the HashMaps.
     */
    private void intializeHashMaps() {
        if (hashFill != null) {
            for (int i = 1; i < 10; i++) {
                if (hashFill.containsKey(i)) {
                    String [] stringsArray  = hashFill.get(i).split("\\(");
                    if (stringsArray[0].equals("image")) {
                        Image img = null;
                        try {
                            String str = stringsArray[1].substring(0,
                                    stringsArray[1].length() - 1);
                            img = ImageIO.read(ClassLoader.
                                    getSystemClassLoader().
                                    getResourceAsStream(str));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        hashImage.put(i, img);
                    } else {
                        hashColor.put(i,
                                ColorsParser.colorFromString(hashFill.get(i)));
                    }
                }
            }
        }
    }
}
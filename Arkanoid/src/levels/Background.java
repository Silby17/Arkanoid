package levels;
import biuoop.DrawSurface;
import sprite.Sprite;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.IOException;

/**
 * Created by Yossi Silberhaft and Binyamin Greenberg.
 */
public class Background implements Sprite {
    private String str;

    /**
     * Constructor Method.
     *
     * @param s the String.
     */
    public Background(String s) {
        this.str = s;
    }

    @Override
    public void drawOn(DrawSurface d) {
        draw(str, d);
    }

    @Override
    public void drawBorder(DrawSurface d) {
    }

    @Override
    public void timePassed(double dt) {
    }

    /**
     * Method that will draw the Background to the screen.
     *
     * @param s the String of the image.
     * @param d the surface to be drawn.
     */
    private void draw(String s, DrawSurface d) {
        String [] stringsArray  = s.split("\\(");
        if (stringsArray[0].equals("image")) {
            Image img = null;
            try {

                String string = stringsArray[1].substring(
                        0, stringsArray[1].length() - 1);
                img = ImageIO.read(ClassLoader.getSystemClassLoader().
                        getResourceAsStream(string));
            } catch (IOException e) {
                e.printStackTrace();
            }
            d.drawImage(0, 0, img);
        } else {
            d.setColor(ColorsParser.colorFromString(s));
            d.fillRectangle(0, 0, 800, 600);
        }
    }
}
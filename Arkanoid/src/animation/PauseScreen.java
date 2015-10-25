package animation;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * Created by Yossi Silberhaft and Binyamin Greenberg.
 */
public class PauseScreen implements Animation {
    /**
     * Constructor Method.
     *
     */
       public PauseScreen() {
    }

    /**
     * Method that will draw the surface at every frame.
     *
     * @param dt the frames per second.
     * @param d The surface to be drawn.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(Color.BLACK);
        d.drawCircle(400, 200, 102);

        d.setColor(Color.WHITE);
        d.fillCircle(400, 200, 100);

        d.setColor(Color.BLACK);
        d.fillCircle(400, 200, 90);

        d.setColor(Color.BLUE);
        d.fillRectangle(370, 150, 20, 90);
        d.fillRectangle(410, 150, 20, 90);

        d.setColor(Color.BLACK);
        d.drawText(50, 500,
                "Game Paused -- Press space to continue", 40);

    }

    /**
     * Check if the screen needs to stop.
     *
     * @return true or false.
     */
    public boolean shouldStop() {
        return false;
    }

}


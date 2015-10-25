package animation;
import arkanoid.Counter;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import java.awt.Color;

/**
 * Created by Yossi Silberhaft and Binyamin Greenberg.
 */
public class EndScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    private GUI gui;
    private Counter score;
    private String string;

    /**
     * Constructor Method.
     *
     * @param c the Counter.
     * @param g the gui.
     * @param s the string.
     */
    public EndScreen(Counter c, GUI g, String s) {

        this.score = c;
        this.gui = g;
        this.stop = false;
        this.string = s;
    }

    /**
     * Method that will draw the screen to the Gui.
     *
     * @param dt the frames per second.
     * @param d The surface to be drawn.
     */
    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(Color.BLACK);
        d.drawText(70, 100,
                string + "Your Score is: " + score.getValue(), 30);

        d.drawText(100, 300, "Press SpaceBar to exit", 30);

    }

    /**
     * Method that will check if the program needs to be terminated.
     *
     * @return true or false.
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
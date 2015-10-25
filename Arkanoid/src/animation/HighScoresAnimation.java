package animation;
import arkanoid.HighScoresTable;
import arkanoid.ScoreInfo;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * Created by Yossi Silberhaft and Binyamin Greenberg.
 * This Class Will display the High Scores in the Gui.
 */
public class HighScoresAnimation implements Animation {
    private Boolean stop;
    private HighScoresTable highScoresTable;

    /**
     * Constructor Method.
     *
     * @param scores scores table.
     */
    public HighScoresAnimation(HighScoresTable scores) {
        this.highScoresTable = scores;
    }

    /**
     * Method that will draw the surface at every frame.
     *
     * @param d The surface to be drawn.
     * @param dt the time frame speed.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(Color.LIGHT_GRAY);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());

        d.setColor(Color.BLUE);
        d.drawText(20, 40, "High Scores", 40);

        d.drawText(100, 120, "Player Name", 30);
        d.drawText(500, 120, "Score", 30);

        d.drawLine(100, 131, 700, 131);

        d.setColor(Color.BLACK);
        for (int i = 0; i < highScoresTable.getHighScores().size(); i++) {
            ScoreInfo scoreInfo = highScoresTable.getHighScores().get(i);
            d.drawText(100, (i * 40) + 155, scoreInfo.getName(), 20);
            d.drawText(500, (i * 40) + 155, "" + scoreInfo.getScore() + "", 20);
        }

        d.setColor(Color.BLACK);
        String msg = "Press space to continue";
        d.drawText(202, 550, msg, 32);
       }

    /**
     * Check if the screen needs to stop.
     *
     * @return true or false.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}
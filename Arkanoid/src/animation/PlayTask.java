package animation;

import java.util.List;

import levels.LevelInformation;
import arkanoid.GameFlow;

/**
 * Created by Yossi Silberhaft.
 * Class that will run the Tasks.
 */
public class PlayTask implements Task<Void> {
    private GameFlow gameFlow;
    private List<LevelInformation> levelInformations;

    /**
     * Constructor Method.
     *
     * @param g the gameFlow.
     * @param l the LevelInformation.
     */
    public PlayTask(GameFlow g, List<LevelInformation> l) {
        this.gameFlow = g;
        this.levelInformations = l;
    }

    /**
     * Method that will run the levelInformation.
     *
     * @return null.
     */
    @Override
    public Void run() {
        gameFlow.runLevels(levelInformations);
        return null;
    }
}
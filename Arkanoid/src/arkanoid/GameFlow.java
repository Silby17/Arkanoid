package arkanoid;
import animation.AnimationRunner;
import animation.GameLevel;
import animation.KeyPressStoppableAnimation;
import animation.EndScreen;
import animation.HighScoresAnimation;
import biuoop.GUI;
import biuoop.DialogManager;
import biuoop.KeyboardSensor;
import levels.LevelInformation;
import java.io.File;
import java.util.List;

/**
 * Created by Yossi Silberhaft Binyamin Greenberg.
 */
public class GameFlow {
    private AnimationRunner animationRunner;
    private KeyboardSensor key;
    private GUI gui;
    private Counter livesCounter = new Counter(3);
    private Counter scoreCounter = new Counter(0);
    private HighScoresTable table;

    /**
     * Controls the Flow of the game.
     *
     * @param ar animation Runner.
     * @param ks the keyboard sensor.
     * @param g the gui received.
     * @param table the highScores table.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI g,
                    HighScoresTable table) {
        animationRunner = ar;
        gui = g;
        key = ks;
        this.table = table;
    }

    /**
     * Method that will run through the levels.
     *
     * @param levels current level.
     */
    public void runLevels(List<LevelInformation> levels) {

        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo,
                    this.animationRunner, this.key, gui,
                    this.livesCounter, this.scoreCounter);
            level.initialize();

            while ((level.getRemainingBlocks() > 0)
                    && (level.getLives() > 0)) {
                level.playOneTurn();
            }

            /*When the player has no more lives the Dialog Box will appear
            * followed by the highScores Screen.*/
            if (level.getLives() == 0) {
                if (table.getRank(this.scoreCounter.getValue())
                        <= table.size()) {
                    DialogManager dialog = gui.getDialogManager();
                    String name = dialog.showQuestionDialog("Name",
                            "What is your name?", "");
                    ScoreInfo currentInfo = new ScoreInfo(name,
                            this.scoreCounter.getValue());
                    table.add(currentInfo);

                    File highScoresTableFile = new File("highscores.txt");
                    try {
                        table.save(highScoresTableFile);
                    } catch (Exception e) {
                        System.out.println(
                                "Problem saving to high scores file!");
                    }
                    this.animationRunner.run(new
                            KeyPressStoppableAnimation(key,
                            KeyboardSensor.SPACE_KEY, new EndScreen(
                            this.scoreCounter, this.gui, "Game over! ")));
                }
            }
        }

        if (livesCounter.getValue() > 0) {
            if (table.getRank(this.scoreCounter.getValue()) <= table.size()) {
                DialogManager dialog = gui.getDialogManager();
                String name = dialog.showQuestionDialog("Name",
                        "What is your name?", "");
                ScoreInfo currentInfo = new ScoreInfo(name,
                        this.scoreCounter.getValue());
                table.add(currentInfo);

                File highScoresTableFile = new File("highscores.txt");
                try {
                    table.save(highScoresTableFile);
                } catch (Exception e) {
                    System.out.println("Problem saving to high scores file!");
                }
                this.animationRunner.run(new KeyPressStoppableAnimation(key,
                        KeyboardSensor.SPACE_KEY, new EndScreen(
                        scoreCounter, gui, "YOU WIN! ")));
            }
        }
        livesCounter = new Counter(3);
        scoreCounter = new Counter(0);

        this.animationRunner.run(new KeyPressStoppableAnimation(key,
                KeyboardSensor.SPACE_KEY, new HighScoresAnimation(table)));
    }
}
import animation.AnimationRunner;
import arkanoid.GameFlow;
import arkanoid.HighScoresTable;
import biuoop.GUI;
import animation.Task;
import java.io.BufferedReader;
import animation.MenuAnimation;
import animation.Menu;
import animation.HighScoreTask;
import animation.PlayTask;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.List;
import levels.LevelInformation;
import levels.LevelSets;
import levels.LevelSpecificationReader;
import levels.SetLevel;
/**
 * This Class will run the full game.
 *
 * @author Yossi Silberhaft and Binyamin Greenberg.
 */
public class Arkanoid {
    private static int width = 800;
    private static int height = 600;

    /**
     * Method that will return the width of the gui.
     *
     * @return width.
     */
    public static int getWidth() {
        return width;
    }

    /**
     * Method that will return the height of the gui.
     *
     * @return height.
     */
    public static int getHeight() {
        return height;
    }


    /**
     * The main Method that will run the program.
     *
     * @param args arguments that will be given by the user.
     * @throws java.io.FileNotFoundException if the file is not found.
     */
    public static void main(String[] args) throws FileNotFoundException {

       /*Creates the new .txt File to hold the high Scores.*/
        String scoresFile = "highscores.txt";
        final File highScoresTableFile = new File(scoresFile);

        HighScoresTable temp = null;
        try {
            if (!highScoresTableFile.exists()) {
                highScoresTableFile.createNewFile();
                temp = new HighScoresTable(3);
                temp.save(highScoresTableFile);
            } else {
                temp = HighScoresTable.loadFromFile(highScoresTableFile);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        final HighScoresTable table = temp;

        final GUI gui = new GUI("Arkanoid", width, height);
        final AnimationRunner animationR = new AnimationRunner(gui, 70);
        final GameFlow gameFlow = new GameFlow(animationR,
                gui.getKeyboardSensor(), gui, table);



        /*Anonymous classes.*/
        Menu<Task<Void>> menu = new
                MenuAnimation<Task<Void>>("Main Menu",
                gui.getKeyboardSensor(), animationR);

        Menu<Task<Void>> subMenu = new MenuAnimation<Task<Void>>(
                "Choose Difficulty", gui.getKeyboardSensor(), animationR);

        Task<Void> quit = new Task<Void>() {
            @Override
            public Void run() {
                System.exit(1);
                return null;
            }
        };

        LevelSets levelSets = null;
        if (args.length > 0 && args != null) {
            BufferedReader argsReader = new
                    BufferedReader(new InputStreamReader(ClassLoader.
                    getSystemClassLoader().getResourceAsStream(args[0])));
            levelSets = LevelSets.fromReader(argsReader);
        } else {
            BufferedReader reader = new BufferedReader(new
                    InputStreamReader(ClassLoader.getSystemClassLoader().
                    getResourceAsStream("level_sets.txt")));
            levelSets = LevelSets.fromReader(reader);
        }

        for (SetLevel level : levelSets.getSetLevelList()) {
            BufferedReader reader2 = new BufferedReader(
                    new InputStreamReader(ClassLoader.
                            getSystemClassLoader().getResourceAsStream(
                            level.getLevelPath())));
            List<LevelInformation> info =
                    LevelSpecificationReader.fromReader(new
                            BufferedReader(reader2));

            subMenu.addSelection(level.getKey(), level.getTitle(),
                    new PlayTask(gameFlow, info));
        }
        menu.addSubMenu("s", "Start Game", subMenu);
        menu.addSelection("h", "high scores",
                new HighScoreTask(animationR, gui, highScoresTableFile));
        menu.addSelection("q", "quit", quit);

        while (true) {
            animationR.run(menu);
            Task<Void> task = menu.getStatus();
            task.run();
        }
    }

    /**
     * Boolean Method that will check if the input is an integer.
     *
     * @param string string to be checked.
     * @return true if it is an Integer, false if not.
     */
    private static boolean checkInteger(String string) {
        int n = 0;
        try {
            n = Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
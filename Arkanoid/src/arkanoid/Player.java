package arkanoid;

/**
 * Created by Yossi Silberhaft and Binyamin Greenberg.
 * This class will Create a Player.
 */
public class Player {
    private Counter scoreCounter;
    private String playerName;
    private int lives;
    private Counter livesCounter;

    /**
     * Method that will set the lives of the player.
     *
     * @param lives amount of lives for the player.
     */
    public Player(int lives) {
        this.lives = lives;
        resetPlayer();
    }

    /**
     * Method that will get the amount of Lives.
     *
     * @return number of lives.
     */
    public Counter getLivesCounter() {
        return this.livesCounter;
    }

    /**
     * Get the score of the player.
     *
     * @return the players Score.
     */
    public Counter getScore() {
        return this.scoreCounter;
    }

    /**
     * Get the player name.
     *
     * @return the players name.
     */
    public String getName() {
        return this.playerName;
    }

    /**
     * Set the players name.
     *
     * @param name set the name of the player.
     */
    public void setName(String name) {
        this.playerName = name;
    }

    /**
     * Method that will create new players each time.
     */
    public void resetPlayer() {
        this.livesCounter = new Counter(this.lives);
        this.scoreCounter = new Counter();
        this.playerName = null;
    }

}



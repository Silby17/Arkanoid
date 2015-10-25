package levels;
import geometry.Velocity;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.BufferedReader;
import sprite.Block;
import sprite.Sprite;

/**
 * Created by Yossi Silberhaft and Binyamin Greenberg.
 * This Class will create the HashMaps for the level information.
 */
public class HashMapLevel implements LevelInformation {
    private HashMap<String, String> hashMap;
    private List<Velocity> initializeBallVelocity;
    private List<String> blocksList;

    /**
     * Constructor Method.
     *
     * @param hashMap    the HashMap.
     * @param blocksList the list of Blocks for the game.
     */
    public HashMapLevel(HashMap<String, String> hashMap,
                        List<String> blocksList) {
        this.hashMap = hashMap;
        this.blocksList = blocksList;
        this.initializeBallVelocity = new ArrayList<Velocity>();
    }

    /**
     * The number of balls for the level.
     *
     * @return the amount of balls.
     */
    @Override
    public int numberOfBalls() {
        return this.hashMap.get("ball_velocities").split(" ").length;
    }

    /**
     * A list of all the ball velocities.
     *
     * @return the ball velocities.
     */

    @Override
    public List<Velocity> initialBallVelocities() {
        String[] velocityArray = this.hashMap.get(
                "ball_velocities").split(" ");
        for (int i = 0; i < velocityArray.length; i++) {
            String[] velocity = velocityArray[i].split(",");
            Velocity tempVelocity = Velocity.
                    fromAngleAndSpeed(Integer.parseInt(velocity[0]),
                            Integer.parseInt(velocity[1]));
            initializeBallVelocity.add(tempVelocity);
        }
        return initializeBallVelocity;
    }

    /**
     * Sets the Paddle Speed.
     *
     * @return the paddle speed.
     */
    @Override
    public int paddleSpeed() {
        return Integer.parseInt(hashMap.get("paddle_speed"));
    }

    /**
     * Sets the paddle width for the level.
     *
     * @return the width of the paddle.
     */
    @Override
    public int paddleWidth() {
        return Integer.parseInt(hashMap.get("paddle_width"));
    }

    /**
     * Sets the name of the level.
     *
     * @return the level name.
     */
    @Override
    public String levelName() {
        return hashMap.get("level_name");
    }

    /**
     * Sets the Background for the level.
     *
     * @return the Level background.
     */
    @Override
    public Sprite getBackground() {
        return new Background(hashMap.get("background"));
    }

    /**
     * The List of blocks for the level.
     *
     * @return the list of blocks needed for the level.
     * @p
     */
    @Override
    public List<Block> blocks() {
        String blockFilePath = "block_definitions";
        int xPos = Integer.parseInt(hashMap.get("blocks_start_x"));
        int yPos = Integer.parseInt(hashMap.get("blocks_start_y"));
        List<Block> returnBlocks = new ArrayList<Block>();
        BlocksFromSymbolsFactory factory = null;
        try {
            factory = BlocksDefinitionReader.fromReader(new
                    BufferedReader(new InputStreamReader(ClassLoader.
                    getSystemClassLoader().getResourceAsStream(hashMap.
                    get("block_definitions")))));

        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < blocksList.size(); i++) {
            char[] line = blocksList.get(i).toCharArray();
            for (int j = 0; j < line.length; j++) {
                if (factory.isBlockSymbol(Character.toString(line[j]))) {
                    Block tempBlock = factory.getBlock(Character.
                            toString(line[j]), xPos, yPos);
                    returnBlocks.add(tempBlock);
                    xPos += tempBlock.getWidth();

                } else if (factory.isSpaceSymbol(Character.toString(line[j]))) {
                    xPos += factory.getSpaceWidth(Character.toString(line[j]));
                }
            }
            yPos += Integer.parseInt(hashMap.get("row_height"));
            xPos = Integer.parseInt(hashMap.get("blocks_start_x"));
        }
        return returnBlocks;
    }

    /**
     * Sets the amount of blocks to be removed from the level.
     *
     * @return the amount of removable blocks.
     */


    @Override
    public int numberOfBlocksToRemove() {
        return Integer.parseInt(hashMap.get("num_blocks"));
    }
}
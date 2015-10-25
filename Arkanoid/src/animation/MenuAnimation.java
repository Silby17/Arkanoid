package animation;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * Created by Yossi Silberhaft and Binyamin Greenberg.
 * Class that will run the Menu Animtaion.
 * @param <T> the Task to be run.
 */
public class MenuAnimation<T> implements Menu<Task<Void>> {
    private HashMap<String, Task<Void>> hashMap =
            new HashMap<String, Task<Void>>();
    private List<String> messageList = new ArrayList<String>();
    private List<String> keyList = new ArrayList<String>();
    private KeyboardSensor keypressed;
    private HashMap<String, Menu<Task<Void>>> hashMapMenu =
            new HashMap<String, Menu<Task<Void>>>();
    private AnimationRunner runner;
    private String title;

    /**
     * Constructor Method.
     *
     * @param k the key.
     * @param r the animation Runner.
     * @param t the title.
     */
    public MenuAnimation(String t, KeyboardSensor k, AnimationRunner r) {
        keypressed = k;
        runner = r;
        this.title = t;
    }
    /**
     * Method that will Display the options on
     * the Menu screen.
     *
     * @param d the Surface to be drawn.
     * @param dt is not used here.
     */
    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(Color.BLUE);
        d.drawText(350, 40, title, 30);
        d.setColor(Color.BLACK);
        d.drawText(20, 85, "Please press one of the Following keys:", 20);

        d.setColor(Color.RED);
        for (int i = 0; i < keyList.size(); i++) {
            d.drawText(150, (i * 50) + 130, '(' + keyList.get(i) + ')', 25);
            d.drawText(190, (i * 50) + 130, messageList.get(i), 25);
        }
    }
    /**
     * Method that will check if a key is pressed.
     *
     * @return true or false.
     */
    @Override
    public boolean shouldStop() {
        for (String string : keyList) {
            if (keypressed.isPressed(string)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Task<Void> getStatus() {

        for (String key : keyList) {
            if (keypressed.isPressed(key)) {
                if (hashMap.containsKey(key)) {
                    return hashMap.get(key);
                } else if (hashMapMenu.containsKey(key)) {
                    runner.run(hashMapMenu.get(key));
                    return hashMapMenu.get(key).getStatus();
                }
            }
        }
        return null;
    }


    @Override
    public void addSelection(String key, String message,
                             Task<Void> returnVal) {
        hashMap.put(key, returnVal);
        messageList.add(message);
        keyList.add(key);
    }

    @Override
    public void addSubMenu(String key, String message,
                           Menu<Task<Void>> subMenu) {
        hashMapMenu.put(key, subMenu);
        messageList.add(message);
        keyList.add(key);
    }

}

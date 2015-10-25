package arkanoid;
/**
 * Created by Yossi Silberhaft on 07/05/2014.
 */
public class Counter {
    private int value;

    /**
     * Constructor Method.
     *
     * @param value of the counter.
     */
    public Counter(int value) {
        this.value = value;
    }

    /**
     * Set the counter to 0.
     */
    public Counter() {
        value = 0;
    }

    /**
     * Add number to current count.
     *
     * @param number after addition.
     */
    public void increase(int number) {
        value = number + value;

    }

    /**
     * Subtract number from the current count.
     *
     * @param number to be subtracted.
     */
    public void decrease(int number) {
        value = value - number;
    }

    /**
     * Gets te current Count.
     *
     * @return the current count.
     */
    public int getValue() {
        return value;
    }
}

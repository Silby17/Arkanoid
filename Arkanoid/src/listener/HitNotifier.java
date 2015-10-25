package listener;
/**
 * Created by Yossi Silberhaft and Binyamin Greenberg.
 */
public interface HitNotifier {
    /** Add hl as a listener to hit events.
     *
     * @param hl the Listener to be added.
     */
    void addHitListener(HitListener hl);

    /** Remove hl from the list of listeners to hit events.
     *
     * @param hl the listener to be removed.
     */
    void removeHitListener(HitListener hl);
}

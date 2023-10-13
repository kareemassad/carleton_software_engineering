
/**
 * The basic Box class to show mutual exclusion and
 * condition synchronization.
 *
 * @author Lynn Marshall
 * @version 1.00
 */
public class Box
{
    private Object contents = null; // contents
    private boolean empty = true; // empty?
    
    /**
     * Puts an object in the box.  This method returns when
     * the object has been put into the box.
     * 
     * @param item The object to be put in the box.
     */
    public synchronized void put(Object item) {
        while (!empty) {
            try {
                wait();
            } catch (InterruptedException e) {
                return;
            }
        }
        contents = item;
        empty = false;
        notifyAll();
    }
    
    /**
     * Gets an object from the box.  This method returns once the
     * object has been removed from the box.
     * 
     * @return The object taken from the box.
     */
    public synchronized Object get() {
        while (empty) {
            try {
                wait();
            } catch (InterruptedException e) {
                return null;
            }
        }
        Object item = contents;
        contents = null;
        empty = true;
        notifyAll();
        return item;
    }

}

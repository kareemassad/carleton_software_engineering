import java.util.concurrent.locks.ReentrantLock;

/**
 * The basic Box class to show mutual exclusion and
 * condition synchronization.
 *
 * @author Lynn Marshall
 * @version 1.00
 */

/**
 * put() only returns when a consumer arrives to consumer the item (get())
 * p1 and c1 control the reentrant lock.
 * After they are done, other threads can use it
 * If state = true, 1 producer and consumer pair can run.
 * This sets state = false
 * When they are done, state = true again
 * and another pair starts
 * If state = false, get() returns null ; put() returns false (state).
 */


public class CoordBox
{
    private Object contents = null; // contents
    private boolean empty = true; // empty?
    private boolean state = true; //true = safe; set to false when in use
    ReentrantLock lock;
    ReentrantLock lockG;

    /**
     * Puts an object in the box.  This method returns when
     * the object has been put into the box.
     *
     * @param item The object to be put in the box.
     */
    public synchronized boolean put(Object item) {
        while (!empty) {
            try {
                wait();
            } catch (InterruptedException e) {
                return false;
            }
        }
        if(lock.tryLock()){
            // true if lock is free
            lock.lock();
            try {
                //"fill the box"
                //only return when a consumer (get()) arrives to consume the item
                contents = item;
                empty = false;
            } finally {
                lock.unlock();
                notifyAll();
                return true;
            }
        } else{
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * Gets an object from the box.  This method returns once the
     * object has been removed from the box.
     *
     * @return The object taken from the box.
     */
    public synchronized Object get() {
        Object item = null;
        if (state == false) {
            return null;
        }
        while (empty) {
            try {
                wait();
            } catch (InterruptedException e) {
                return null;
            }
        }
        if (lockG.tryLock()){
            lockG.lock();
            try {
                item = contents;
                contents = null;
                empty = true;

            }finally {
                notifyAll();
                return item;
            }
        }else {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}

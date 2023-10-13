/**
 * In this question, you are going to add three new methods to the Box class given on the last page of this
 * exam. As mentioned at the top of the exam, you may remove the last page from the exam. The new
 * methods are replaceContents, replaceContentsWhenFull, and isFull.
 *
 * replaceContents has one parameter, an Object, and does not return anything (i.e. it’s a
 * void method). Regardless of the current Box state (i.e. whether it is empty or not), this
 * method sets the contents of the Box to the Object parameter.
 *
 * replaceContentsWhenFull has one parameter, an Object, and does not return
 * anything. It is similar to method replaceContents, above, but it may only proceed if the
 * Box is not empty. In other words, when the Box is full it replaces the contents with the
 * Object parameter.
 *
 * isFull has no parameters and returns a Boolean. If the Box is empty it returns false,
 * otherwise it returns true.
 */
public class ModBox {

    private Object contents = null;
    private boolean empty = true;

    public synchronized void replaceContents(Object obj) {
        contents = obj;
        if (empty) {
            empty = false;
            notifyAll();
        }
    }

    public synchronized void replaceContentsWhenFull(Object obj) {
        while (!isFull()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        contents = obj;
    }

    public synchronized boolean isFull() {
        return !empty;
    }
}
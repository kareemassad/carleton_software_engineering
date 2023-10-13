/**
 * In this question, you are going to rewrite the Box class (see the last page
 * of the exam) with
 * some changes. In addition to providing an object, the put method now provides
 * a count
 * indicating how many consumers will remove this object from the box before the
 * box becomes
 * empty. For example, if a producer invoked box1.put(o1,3), then the next three
 * consumers
 * to invoke box1.get() would all get o1. Only after o1 has been removed 3 times
 * will box1
 * become empty. At that point, another object may be put in box1 (with its
 * count specifying
 * how many consumers will get that object), etc. Ensure that your class
 * contains sufficient
 * internal synchronization to work properly in a multi-threaded environment.
 * Call your new
 * class MultiBox. (Write the entire class, including all fields and both
 * methods.)
 */
public class MultiBox {
    private Object contents = null;
    private int count = 0;

    public synchronized void put(Object item, int count) {

        if (count <= 0)
            return;
        while (this.count > 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                return;
            }
        }
        contents = item;
        this.count = count;
        notifyAll();
    }

    public synchronized Object get() {
        while (count == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                return null;
            }
        }
        count--;
        if (count == 0)
            notifyAll(); // the count has gone from above 0 to 0
        return contents;
    }
}
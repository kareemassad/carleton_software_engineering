public class Barrier {

    private final int n;
    private int curr;

    public Barrier(int n) {
        curr = 0;
        this.n = n;
    }

    public boolean checkpoint() {
        curr++;
        synchronized (this) {
            while (curr < n) {
                try {
                    wait(10000); // all threads are here
                } catch (InterruptedException e) {
                    return false;
                }
            }
            notifyAll();
            return true;
        }
    }
}

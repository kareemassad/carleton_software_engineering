/*
 * Test -- start 3 threads and try out barrier.  Priorities needed!
 */

import java.lang.Runnable;
import java.lang.Thread;
import java.util.Vector;

public class BarrierTest1 extends Thread {
    private Barrier _barrier;
    private boolean _done;

    public BarrierTest1(Barrier barrier) {
        _barrier = barrier;
        _done = false;
    }

    public void run() {
        System.out.println("Checkpoint called.");
        if (_barrier.checkpoint() == false) {
            System.out.println("Call to checkpoint fails");
            return; /* Don't set done. */
        }
        _done = true;
        System.out.println("Checkpoint returns!");
    }

    public boolean done() {
        return _done;
    }

    public static void main(String args[]) {
        int count = 3;
        Barrier barrier = new Barrier(count);
        Vector<BarrierTest1> threads = new Vector<BarrierTest1>(count);

        System.out.println("Test 1 starts!");

        for (int i = 0; i < count; ++i) {
            threads.add(new BarrierTest1(barrier));
        }
        for (BarrierTest1 thread : threads) {
            thread.start();
        }
        try {
            for (BarrierTest1 thread : threads) {
                thread.join(100);
            }
        } catch (InterruptedException e) {
        }
        boolean fail = false;
        for (BarrierTest1 thread : threads) {
            if (!thread.done())
                fail = true;
        }

        System.out.println("Test 1 " + (fail ? "fails" : "passes"));
    }

}
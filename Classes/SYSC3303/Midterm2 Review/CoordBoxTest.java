/** BoxTest.java
 *
 * Testing Box by creating a producer and consumer.
 * 
 * @author Lynn Marshall
 * @version 1.00
 * 
 */

public class CoordBoxTest
{
    public static void main(String[] args)
    {
        Thread producer1, consumer1;
        Thread producer2, consumer2;
        CoordBox box;

        box = new CoordBox(); // shared by producer and consumer

        // Create the producer and consumer threads, passing each thread
        // a reference to the shared BoundedBuffer object.
        //put() then get()
        producer1 = new Thread(new Producer1(box),"Producer1");
        consumer1 = new Thread(new Consumer1(box),"Consumer1");
        producer1.start();
        consumer1.start();

        //put() then get()
        //producer2 = new Thread(new Producer1(box),"Producer2");
        //consumer2 = new Thread(new Consumer1(box),"Consumer2");
        //consumer2.start();
        //producer2.start();

    }
}

/**
 * Producer is the class for the producer thread.
 */
class Producer1 implements Runnable
{ 
    private CoordBox box;

    public Producer1(CoordBox box)
    {
        this.box = box;
    }

    public void run()
    {
        //Integer item = new Integer(i);
        Integer item = 1;
        System.out.println(Thread.currentThread().getName() + " produced " + item);
        box.put(item);
        System.out.println(Thread.currentThread().getName() + " put in box " + item);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {}

    }
}

/**
 * Consumer is the class for the consumer thread.
 */
class Consumer1 implements Runnable
{
    private CoordBox box;
    
    public Consumer1(CoordBox box)
    {
        this.box = box;
    }

    public void run()
    {
        System.out.println(Thread.currentThread().getName() + " ready to consume.");
        Object item = box.get();
        System.out.println(Thread.currentThread().getName() + " consumed " + item);
        try {
            Thread.sleep(1000); // change to 100 to see difference
        } catch (InterruptedException e) {}

    }
}


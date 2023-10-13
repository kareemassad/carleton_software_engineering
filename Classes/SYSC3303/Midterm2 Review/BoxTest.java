/** BoxTest.java
 *
 * Testing Box by creating a producer and consumer.
 * 
 * @author Lynn Marshall
 * @version 1.00
 * 
 */

public class BoxTest
{
    public static void main(String[] args)
    {
        Thread producer, consumer;
        Box box;

        box = new Box(); // shared by producer and consumer

        // Create the producer and consumer threads, passing each thread
        // a reference to the shared BoundedBuffer object.
        producer = new Thread(new Producer(box),"Producer");
        consumer = new Thread(new Consumer(box),"Consumer");
        producer.start();
        consumer.start();
    }
}

/**
 * Producer is the class for the producer thread.
 */
class Producer implements Runnable
{ 
    private Box box;

    public Producer(Box box)
    {
        this.box = box;
    }

    public void run()
    {
        for(int i = 0; i < 10; i++) {
            Integer item = new Integer(i);
            System.out.println(Thread.currentThread().getName() + " produced " + item);
            box.put(item);
            System.out.println(Thread.currentThread().getName() + " put in box " + item);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {}
        }
    }
}

/**
 * Consumer is the class for the consumer thread.
 */
class Consumer implements Runnable
{
    private Box box;
    
    public Consumer(Box box)
    {
        this.box = box;
    }

    public void run()
    {
        for(int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + " ready to consume.");
            Object item = box.get();
            System.out.println(Thread.currentThread().getName() + " consumed " + item);
            try {
                Thread.sleep(1000); // change to 100 to see difference
            } catch (InterruptedException e) {}
        }
    }
}


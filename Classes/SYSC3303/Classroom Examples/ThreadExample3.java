/**
 * ThreadExample3.java - this application demonstrates how to create multiple
 * independent threads of execution from two Runnable classes.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class ThreadExample3 extends JFrame
{
    /**
     * JTextArea for the factorial thread.
     */
    private JTextArea ta1;

    /**
     * JTextArea for the second factorial thread.
     */
    private JTextArea ta2;

    /**
     * JTextArea for the fibonacci thread.
     */
    private JTextArea ta3;

    /**
     * JTextArea for the thread executing main().
     */
    private JTextArea status;

    /**
     * Build the GUI.
     */
    public ThreadExample3(String title) {
        super(title);
        Box box = Box.createVerticalBox();

        ta1 = new JTextArea(5,40);
        ta1.setEditable(false);
        JScrollPane pane1 =
            new JScrollPane(ta1, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pane1.setBorder(BorderFactory.createTitledBorder("Thread 1"));
        box.add(pane1);

        ta2 = new JTextArea(5,40);
        ta2.setEditable(false);
        JScrollPane pane2 =
            new JScrollPane(ta2, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pane2.setBorder(BorderFactory.createTitledBorder("Thread 2"));
        box.add(pane2);

        ta3 = new JTextArea(5,40);
        ta3.setEditable(false);
        JScrollPane pane3 =
            new JScrollPane(ta3, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pane3.setBorder(BorderFactory.createTitledBorder("Thread 3"));
        box.add(pane3);

        status = new JTextArea(5, 40);
        status.setEditable(false);
        JScrollPane pane4 =
            new JScrollPane(status, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pane4.setBorder(BorderFactory.createTitledBorder("Status"));
        box.add(pane4);

        getContentPane().add(box);
    }

    public static void main(String[] args) {
        ThreadExample3 frame = new ThreadExample3("Thread Example 3");

        /* Instantiate an anonymous subclass of WindowAdapter, and register it
         * as the frame's WindowListener.
         * windowClosing() is invoked when the frame is in the process of being
         * closed to terminate the application.
         */
        frame.addWindowListener(
            new WindowAdapter() {
               public void windowClosing(WindowEvent e) {
                  System.exit(0);
               }
            }
        );

        // Size the window to fit the preferred size and layout of its
        // subcomponents, then show the window.
        frame.pack();
        frame.setVisible(true);

        Thread factorialThread = new Thread(
                 new Factorial(frame.ta1), "Factorial calculator");
        frame.status.append("Created: " + factorialThread + '\n');

        Thread secondFactorialThread = new Thread(
                 new Factorial(frame.ta2), "Second factorial calculator");
        frame.status.append("Created: " + secondFactorialThread + '\n');

        Thread fibonnaciThread = new Thread(
                 new Fibonacci(frame.ta3), "Fibonacci calculator");
        frame.status.append("Created: " + fibonnaciThread + '\n');

        frame.status.append("Starting threads\n");
        factorialThread.start();
        secondFactorialThread.start();
        fibonnaciThread.start();
    }
}

/**
 * This thread calculates 0! through 20!, where
 *  0! = 1
 *  n! = n * (n-1)!, n > 0
 */
class Factorial implements Runnable
{
    /**
     * The text area where this thread's output will be displayed.
     */
    private JTextArea transcript;

    public Factorial(JTextArea transcript) {
        this.transcript = transcript;
    }

    public void run() {
        // 0! = 1
        long factorial = 1;
        transcript.append("0! = " + factorial + '\n');
        for (int n = 1; n <= 20; n++) {
           // Sleep for between 0 and 2 seconds before calculating n!
            try {
                Thread.sleep((int)(Math.random() * 2000));
            } catch (InterruptedException e) {}

            // n! = n * (n-1)!
            factorial = n * factorial;
            transcript.append(n + "! = " + factorial + '\n');
        }
        transcript.append(Thread.currentThread() + " finished\n");
    }
}

/**
 * This thread calculates fib(1) through fib(20), where
 * fib(1) = 1
 * fib(2) = 1
 * fib(n) = fib(n-1) + fib(n-2), n >2
 */
class Fibonacci implements Runnable
{
    /**
     * The text area where this thread's output will be displayed.
     */
    JTextArea transcript;

    public Fibonacci(JTextArea transcript) {
        this.transcript = transcript;
    }

    public void run() {
        // fib(1) = 1
        int firstFib = 1;
        transcript.append("fib(1) = " + firstFib + '\n');

        try {
            Thread.sleep((int)(Math.random() * 2000));
        } catch (InterruptedException e) {}

        // fib(2) = 1
        int secondFib = 1;
        transcript.append("fib(2) = " + secondFib + '\n');

        for (int n = 3; n <= 20; n++) {
           try {
               Thread.sleep((int)(Math.random() * 2000));
            } catch (InterruptedException e) {}
                    
            // fib(n) = fib(n-1) + fib(n-2)
            int fibN = firstFib + secondFib;
            transcript.append("fib(" + n + ") = " + fibN + '\n');
            secondFib = firstFib;
            firstFib = fibN;
        }
        transcript.append(Thread.currentThread() + " finished\n");
    }
}


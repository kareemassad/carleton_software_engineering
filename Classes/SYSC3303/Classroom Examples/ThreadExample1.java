/**
 * ThreadExample1.java - this application demonstrates how to define a
 * separate thread of execution by subclassing java.lang.Thread.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class ThreadExample1 extends JFrame
{
    /**
     * JTextArea for the factorial thread.
     */
    private JTextArea ta1;

    /**
     * JTextArea for the thread executing main().
     */
    private JTextArea status;

    /**
     * Build the GUI.
     */
    public ThreadExample1(String title) {
        super(title);
        Box box = Box.createVerticalBox();

        ta1 = new JTextArea(5,40);
        ta1.setEditable(false);
        JScrollPane pane1 =
            new JScrollPane(ta1, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pane1.setBorder(BorderFactory.createTitledBorder("Thread 1"));
        box.add(pane1);

        status = new JTextArea(5, 40);
        status.setEditable(false);
        JScrollPane pane2 =
            new JScrollPane(status, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pane2.setBorder(BorderFactory.createTitledBorder("Status"));
        box.add(pane2);

        getContentPane().add(box);
    }

    public static void main(String[] args) {
        ThreadExample1 frame = new ThreadExample1("Thread Example 1");

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

        Thread factorialThread =
           new Factorial("Factorial calculator", frame.ta1);
        frame.status.append("Created: " + factorialThread + '\n');

        frame.status.append("Starting thread\n");
        factorialThread.start();
    }
}

/**
 * This thread calculates 0! through 20!, where
 *  0! = 1
 *  n! = n * (n-1)!, n > 0
 */
class Factorial extends Thread
{
    /**
     * The text area where this thread's output will be displayed.
     */
    private JTextArea transcript;

    public Factorial(String name, JTextArea transcript) {
        super(name);
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


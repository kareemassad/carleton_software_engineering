import Models.Game;
import Models.Player;
import Models.Property;

import javax.swing.*;
// import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Turns the input from the Play() class and displays it in the JTextArea on the
 * GUI
 * 
 * @author Keefer Belanger
 */
public class JTextAreaOutputStream extends OutputStream {
    private final JTextArea playerTextArea;

    /**
     * Constructor for the JTextArea output class
     * 
     * @param playerTextArea
     */
    public JTextAreaOutputStream(JTextArea playerTextArea) {
        this.playerTextArea = playerTextArea;
    }

    public static void directingMessageToTextArea(JTextArea playerTextArea) {
        try {
            PrintStream printStream = new PrintStream(new JTextAreaOutputStream(playerTextArea));

            // PrintStream standardOut = System.out;
            // PrintStream standardErr = System.err;

            System.setOut(printStream);
            System.setErr(printStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Takes the print string from all System.out.print and puts it into the
     * JTextArea
     * 
     * @param buffer
     * @param offset
     * @param length
     * @throws IOException
     */
    @Override
    public void write(byte[] buffer, int offset, int length) throws IOException {
        // directs print statements to the text area
        playerTextArea.append(new String(buffer, offset, length));

        // scrolls the text area to the end of the print statements
        playerTextArea.setCaretPosition(playerTextArea.getDocument().getLength());
    }

    /**
     * Takes the print string and changes it to be able to be put into the JTextArea
     * 
     * @param b
     * @throws IOException
     */
    @Override
    public void write(int b) throws IOException {
        // directs print statements to the text area
        playerTextArea.append(String.valueOf((char) b));

        // scrolls the text area to the end of the print statements
        playerTextArea.setCaretPosition(playerTextArea.getDocument().getLength());
    }

}

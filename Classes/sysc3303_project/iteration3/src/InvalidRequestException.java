
/**
 * A custom exception class to handle invalid request error
 * @Author Igor Bogdanov, 101169300
 */


public class InvalidRequestException extends Exception {

    /**
     * a regular constructor to create InvalidPacketException
     * @param message message used for the exception
     */
    public InvalidRequestException (String message) {
        super(message);
    }

}
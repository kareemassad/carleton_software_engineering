package Helpers;

public class ErrorLog {

    String  errorMessage;
    // TODO add text file here that logs error?

    public ErrorLog() {

    }

    public ErrorLog(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String toString() {
        return this.errorMessage;
    }
}
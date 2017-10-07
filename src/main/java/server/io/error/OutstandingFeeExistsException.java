package main.java.server.io.error;

public class OutstandingFeeExistsException extends Exception {
    @Override
    public String getMessage () {
        return "Outstanding Fee Exists!";
    }
}

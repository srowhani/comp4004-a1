package main.java.server.io.error;

public class PendingLoansExistException extends Exception {
    @Override
    public String getMessage () {
        return "Pending loans exist!";
    }
}

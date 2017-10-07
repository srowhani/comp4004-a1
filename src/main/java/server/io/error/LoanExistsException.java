package main.java.server.io.error;

public class LoanExistsException extends Exception {
    @Override
    public String getMessage () {
        return "Outstanding Loan Exists!";
    }
}

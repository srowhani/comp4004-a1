package main.java.server.io.error;

public class NoSuchLoanExistsException extends Exception {
    @Override
    public String getMessage () {
        return "No Such Loan Exists!";
    }
}

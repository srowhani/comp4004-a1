package main.java.server.io.error;

public class InvalidUserCredentialsException extends Exception {
    @Override
    public String getMessage () {
        return "Invalid User Credentials!";
    }
}

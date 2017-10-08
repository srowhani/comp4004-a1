package main.java.server.io.error;

public class UserEntityExistsException extends Exception {
    @Override
    public String getMessage () {
        return "Title not found!";
    }
}

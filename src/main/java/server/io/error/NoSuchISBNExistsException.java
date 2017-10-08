package main.java.server.io.error;

public class NoSuchISBNExistsException extends Exception {
    @Override
    public String getMessage () {
        return "No such ISBN exists!";
    }
}

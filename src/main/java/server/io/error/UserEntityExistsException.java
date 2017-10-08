package main.java.server.io.error;

public class UserEntityExistsException extends Exception {
    @Override
    public String getMessage () {
        return "User Entity Exists!";
    }
}

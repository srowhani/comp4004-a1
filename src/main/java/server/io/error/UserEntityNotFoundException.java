package main.java.server.io.error;

public class UserEntityNotFoundException extends Exception {
    @Override
    public String getMessage () {
        return "User not found!";
    }
}

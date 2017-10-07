package main.java.server.io.error;

public class ItemEntityNotFoundException extends Exception {
    @Override
    public String getMessage () {
        return "Item Entity Not Found!";
    }
}

package main.java.server.io.error;

public class ItemNotAvailableException extends Exception {
    @Override
    public String getMessage () {
        return "Item Not Available!";
    }
}

package main.java.server.io.error;

public class ItemAlreadyRenewedException extends Exception {
    @Override
    public String getMessage () {
        return "Item Already Renewed!";
    }

}

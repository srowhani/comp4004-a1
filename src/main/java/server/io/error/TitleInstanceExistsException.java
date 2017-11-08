package main.java.server.io.error;

public class TitleInstanceExistsException extends Exception {
    @Override
    public String getMessage () {
        return "Item Instance Exists. Cannot Delete Title!";
    }
}

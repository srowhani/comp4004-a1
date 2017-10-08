package main.java.server.io.error;

public class TitleEntityNotFoundException extends Exception {
    @Override
    public String getMessage () {
        return "Title not found!";
    }
}

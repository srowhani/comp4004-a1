package main.java.server.io.error;

public class TitleEntityExistsException extends Exception {
    @Override
    public String getMessage () {
        return "Title already exists!";
    }
}

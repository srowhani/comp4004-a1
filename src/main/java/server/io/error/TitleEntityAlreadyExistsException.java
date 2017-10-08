package main.java.server.io.error;

public class TitleEntityAlreadyExistsException extends Exception {
    @Override
    public String getMessage () {
        return "Title already exists!";
    }
}

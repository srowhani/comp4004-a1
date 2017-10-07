package main.java.server.io.handler.model;

public class ServerOutput extends Output {
    @Override
    public String toString() {
        return String.format("[%s, %s]", getOutput(), getState());
    }
}

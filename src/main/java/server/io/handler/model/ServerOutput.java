package main.java.server.io.handler.model;

public class ServerOutput extends Output {

    public ServerOutput () {
        super();
    }
    public ServerOutput (String output, ClientState state) {
        super(output, state);
    }
    @Override
    public String toString() {
        return String.format("[%s, %s]", getOutput(), getState());
    }
}

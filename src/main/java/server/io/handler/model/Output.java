package main.java.server.io.handler.model;

public abstract class Output {
    private String output;
    private ClientState state;

    public Output() {

    }

    public Output(String output, ClientState state) {
        this.output = output;
        this.state = state;
    }

    @Override
    public abstract String toString();

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public ClientState getState() {
        return state;
    }

    public void setState(ClientState state) {
        this.state = state;
    }
}

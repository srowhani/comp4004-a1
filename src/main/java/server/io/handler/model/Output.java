package main.java.server.io.handler.model;

public abstract class Output {
	private String output;
	private OutputState state;

	public Output () {

	}

	public Output(String output, OutputState state) {
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

	public OutputState getState() {
        return state;
    }

    public void setState(OutputState state) {
        this.state = state;
    }
}

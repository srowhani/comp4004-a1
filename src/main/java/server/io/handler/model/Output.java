package main.java.server.io.handler.model;

public abstract class Output {
	private String output;
    // TODO: Refactor to ENUM
	private int state;

	public Output(String output, int state) {
		this.output = output;
		this.state = state;
	}

	@Override
	public String toString() {
		return String.format("[%s, %s]", output, state);
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

}

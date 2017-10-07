package main.java.server.io.handler.model;

import main.java.server.network.ServerThread;

public class Client {
	private ServerThread serverThread;
	private ClientState state;

	public Client () {
        this.serverThread = null;
        this.state = null;
	}

	public Client(ServerThread serverThread, ClientState state){
		this.serverThread = serverThread;
		this.state = state;
	}
	public ServerThread getServerThread() {
		return serverThread;
	}
	public void setServerThread(ServerThread client) {
		this.serverThread = serverThread;
	}
	public ClientState getState() {
		return state;
	}
	public void setState(ClientState state) {
		this.state = state;
	}

}

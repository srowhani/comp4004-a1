package main.java.client;


import main.java.util.Config;

public class StartTerminals {
	public static void main(String[] argv) {
		new LibClient(Config.DEFAULT_HOST, Config.DEFAULT_PORT);
	}
}

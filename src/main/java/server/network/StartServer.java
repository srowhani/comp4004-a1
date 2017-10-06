package main.java.server.network;


import main.java.util.Config;
import main.java.util.Trace;
import org.apache.log4j.Logger;


public class StartServer {
	private static Logger logger = Trace.getInstance().getLogger();
	public static void main(String[] argv) {
		logger.info(String.format("Starting server on %s:%s", Config.DEFAULT_HOST, Config.DEFAULT_PORT));
		new LibServer(Config.DEFAULT_PORT);
	}
}

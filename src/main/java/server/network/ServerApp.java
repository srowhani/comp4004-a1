package main.java.server.network;


import main.java.util.Config;
import main.java.util.Trace;
import org.apache.log4j.Logger;


public class ServerApp {
    private static Logger logger = Trace.getInstance().getLogger("operation_file");

    public static void main(String[] argv) {
        LibServer libServer = new LibServer(Config.DEFAULT_PORT);
        libServer.start();
        logger.info(String.format("Starting server on %s:%s", Config.DEFAULT_HOST, Config.DEFAULT_PORT));
    }
}

import main.java.server.io.handler.ClientInputHandler;
import main.java.server.io.handler.ClientInputReader;
import main.java.server.io.handler.model.ClientState;
import main.java.server.io.handler.model.ServerOutput;
import main.java.util.Trace;
import org.apache.log4j.Logger;

import java.util.concurrent.CompletableFuture;

public class AcceptanceTest {
    private Logger logger = Trace.getInstance().getLogger("operation_file");

    private ClientInputReader clientInputReader;

    public AcceptanceTest () {
        clientInputReader = new ClientInputReader().pipe(new ClientInputHandler());
    }

    public Logger getLogger () {
        return logger;
    }

    public CompletableFuture<ServerOutput> input(String input, ClientState state) {
        return CompletableFuture.completedFuture(clientInputReader.processInput(input, state));
    }

    public ServerOutput now(CompletableFuture<ServerOutput> serverOutputCompletableFuture) {
        return serverOutputCompletableFuture.getNow(null);
    }
}

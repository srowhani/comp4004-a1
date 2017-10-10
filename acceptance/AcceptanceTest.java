import main.java.server.io.dao.FeeTable;
import main.java.server.io.dao.ItemTable;
import main.java.server.io.dao.LoanTable;
import main.java.server.io.dao.UserTable;
import main.java.server.io.handler.ClientInputHandler;
import main.java.server.io.handler.ClientInputReader;
import main.java.server.io.handler.model.ClientState;
import main.java.server.io.handler.model.ServerOutput;
import main.java.util.Config;
import main.java.util.Trace;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;

import java.util.concurrent.CompletableFuture;

import static main.java.server.io.handler.model.ClientState.FINISHWAITING;

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

    public CompletableFuture<ServerOutput> clerkLogin () {
        return input("Clerk", FINISHWAITING)
            .thenApply(output -> now(input(Config.CLERK_PASSWORD, output.getState())));
    }
}

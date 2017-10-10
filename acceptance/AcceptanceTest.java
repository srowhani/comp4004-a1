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
import java.util.concurrent.ExecutionException;

import static main.java.server.io.handler.model.ClientState.CREATEUSER;
import static main.java.server.io.handler.model.ClientState.DELETEUSER;
import static main.java.server.io.handler.model.ClientState.FINISHWAITING;
import static org.junit.Assert.assertEquals;

public class AcceptanceTest {
    private Logger logger = Trace.getInstance().getLogger("operation_file");

    private ClientInputReader clientInputReader;

    public AcceptanceTest () {
        clientInputReader = new ClientInputReader().pipe(new ClientInputHandler());
    }

    public void scenario (String scenario) {
        logger.info("===========");
        logger.info(String.format("\tAcceptance: %s", scenario));
        logger.info("===========");
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

    public void assertSuccess(ServerOutput s) {
        assertEquals("Success!", s.getOutput());
    }

    public CompletableFuture<ServerOutput> addItem(String isbn) throws ExecutionException, InterruptedException {
        return clerkLogin().thenApply(output -> now(input("create item", output.getState())))
                .thenApply(output -> {
                    assertEquals("Please Input Item Info:'ISBN'", output.getOutput());
                    assertEquals(ClientState.CREATEITEM, output.getState());;
                    return now(input(isbn, output.getState()));
                });
    }

    public CompletableFuture<ServerOutput> createTitle(String isbn, String title) {
        return clerkLogin().thenApply(o -> now(input("create title", o.getState())))
                .thenApply(output -> {
                    assertEquals("Please Input Title Info:'ISBN,title'", output.getOutput());
                    assertEquals(ClientState.CREATETITLE, output.getState());
                    return now(input(String.format("%s,%s", isbn, title), output.getState()));
                });
    }

    public CompletableFuture<ServerOutput> deleteTitle (String isbn) {
        return clerkLogin().thenApply(output -> now(input("delete title", output.getState())))
                .thenApply(output -> {
                    assertEquals("Please Input Title Info:'ISBN'", output.getOutput());
                    assertEquals(ClientState.DELETETITLE, output.getState());;
                    return now(input(isbn, output.getState()));
                });
    }

    public CompletableFuture<ServerOutput> createUser(String username, String password) throws ExecutionException, InterruptedException {
        return clerkLogin()
                .thenApply(output -> now(input("create user", output.getState())))
                .thenApply(output -> {
                    assertEquals("Please Input User Info:'username,password'", output.getOutput());
                    assertEquals(CREATEUSER, output.getState());
                    return now(input(String.format("%s,%s", username, password), output.getState()));
                });
    }

    public CompletableFuture<ServerOutput> deleteUser(String username) throws ExecutionException, InterruptedException {
        return clerkLogin()
                .thenApply(output -> now(input("delete user", output.getState())))
                .thenApply(output -> {
                    assertEquals("Please Input User Info:'useremail'", output.getOutput());
                    assertEquals(DELETEUSER, output.getState());
                    return now(input(username, output.getState()));
                });
    }

    public CompletableFuture<ServerOutput> borrowItem (String username, String isbn, String copyNumber) throws ExecutionException, InterruptedException {
        return input("borrow", ClientState.USER)
        .thenApply(r -> now(input(String.format("%s,%s,%s", username, isbn, copyNumber), r.getState())));
    }

    public CompletableFuture<ServerOutput> returnItemLater (String username, String isbn, String copyNumber, int daysLater) throws ExecutionException, InterruptedException {
        return input("return", ClientState.USER)
        .thenApply(r -> now(input(String.format("%s,%s,%s,%d", username, isbn, copyNumber, daysLater), r.getState())));
    }

    public CompletableFuture<ServerOutput> renewItem (String username, String isbn, String copyNumber) throws ExecutionException, InterruptedException {
        return input("renew", ClientState.USER)
                .thenApply(r -> now(input(String.format("%s,%s,%s", username, isbn, copyNumber), r.getState())));
    }
}

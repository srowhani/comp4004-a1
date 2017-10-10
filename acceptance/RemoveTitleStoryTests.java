import main.java.server.io.handler.model.ClientState;
import main.java.server.io.handler.model.ServerOutput;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static main.java.server.io.handler.model.ClientState.CREATEUSER;
import static org.junit.Assert.assertEquals;

public class RemoveTitleStoryTests extends AcceptanceTest {
    // All items associated with title, by ISBN, can no longer be loaned.
    @Test
    public void deleteTitleAttemptAccessAssociatedItems () throws ExecutionException, InterruptedException {
        String isbn = "9999999999999";
        String username = "johnny";
        createUser(username, "pass");
        createTitle(isbn, "Johnny eats too many appleseeds").get();
        addItem(isbn).get();
        deleteTitle(isbn).get();
        input("borrow", ClientState.USER)
        .thenApply(result -> now(input(String.format("%s,%s,%d", username, isbn, 0), result.getState())))
        .thenAccept(result -> assertEquals("No such ISBN exists!", result.getOutput())).get();
    }
    // If some of the title’s item instances are being loaned out, you’re not allowed to remove.
    @Test
    public void deleteTitleWhileInstanceLoanedOut () throws ExecutionException, InterruptedException {
        String isbn = "9999999999998";
        String username = "johnny-1";
        createUser(username, "pass");
        createTitle(isbn, "Johnny eats not enough appleseeds").get();
        addItem(isbn).get();

        input("borrow", ClientState.USER)
                .thenApply(result -> now(input(String.format("%s,%s,%d", username, isbn, 0), result.getState())))
                .thenAccept(this::assertSuccess).get();

        deleteTitle(isbn)
        .thenAccept(result -> {
            assertEquals("Outstanding Loan Exists!", result.getOutput());
        })
        .get();
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
}

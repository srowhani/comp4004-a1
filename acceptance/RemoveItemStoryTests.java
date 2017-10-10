import main.java.server.io.handler.model.ClientState;
import main.java.server.io.handler.model.ServerOutput;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static main.java.server.io.handler.model.ClientState.CLERK;
import static main.java.server.io.handler.model.ClientState.CREATEUSER;
import static org.junit.Assert.assertEquals;

public class RemoveItemStoryTests extends AcceptanceTest {
    // Item cannot be accessed upon removal
    @Test
    public void itemAccessAfterRemoval () throws ExecutionException, InterruptedException {
        createItem("9781317594277")
        .thenApply(output -> now(input("delete item", output.getState())))
        .thenApply(output -> now(input("9781317594277,0", output.getState())))
        .thenAccept(this::assertSuccess)
        .get();

        input("borrow", ClientState.CLERK)
        .thenApply(output -> now(input("Zhibo@carleton.ca,9781317594277,0", output.getState())))
        .thenAccept(output -> {
            assertEquals("Item Entity Not Found!", output.getOutput());
        }).get();

    }
    // If item is being loaned, the librarian will not be allowed to remove.
    @Test
    public void itemCannotBeRemovedWhileLoaned () throws ExecutionException, InterruptedException {
        createItem("9781317594277").get();
        createUser("test-user-666", "pass").get();

        input("borrow", ClientState.CLERK)
        .thenApply(output -> now(input("test-user-666,9781317594277,0", output.getState())))
        .thenAccept(this::assertSuccess).get();

        input("delete item", ClientState.CLERK)
        .thenApply(output -> now(input("9781317594277,0", output.getState())))
        .thenAccept(output -> {
            assertEquals("Outstanding Loan Exists!", output.getOutput());
        }).get();
    }

    public CompletableFuture<ServerOutput> createItem(String isbn) throws ExecutionException, InterruptedException {
        return clerkLogin().thenApply(output -> now(input("create item", output.getState())))
        .thenApply(output -> {
            assertEquals("Please Input Item Info:'ISBN'", output.getOutput());
            assertEquals(ClientState.CREATEITEM, output.getState());;
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

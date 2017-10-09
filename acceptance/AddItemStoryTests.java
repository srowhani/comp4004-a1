import main.java.server.io.handler.model.ClientState;
import main.java.server.io.handler.model.ServerOutput;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static main.java.server.io.handler.model.ClientState.CREATETITLE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Maps to test 1
 */
public class AddItemStoryTests extends AcceptanceTest {
    @Test
    public void addItemIfDoesntExist() throws ExecutionException, InterruptedException {
        getLogger().info("Story Test: Add Item");
        input("create item", ClientState.CLERK)
            .thenApply(output -> {
                assertEquals("Please Input Item Info:'ISBN'", output.getOutput());
                assertEquals(ClientState.CREATEITEM, output.getState());
                return input("9781442668584", output.getState()).getNow(null);
            }).thenAccept(output -> {
                assertEquals("Success!", output.getOutput());
                assertEquals(ClientState.CLERK, output.getState());
            }).get();
    }

    @Test
    public void addItemTitleDoesntExist() throws ExecutionException, InterruptedException {
        input("create item", ClientState.CLERK)
        .thenApply(output -> {
            assertEquals("Please Input Item Info:'ISBN'", output.getOutput());
            assertEquals(ClientState.CREATEITEM, output.getState());
            return input("1234567891035", output.getState()).getNow(null);
        }).thenAccept(output -> {
            assertEquals("The Title Does Not Exists! Would you like to add it?", output.getOutput());
            assertEquals(ClientState.CLERK, output.getState());
        }).get();
    }
}

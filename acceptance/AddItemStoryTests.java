import main.java.server.io.handler.model.ClientState;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;

/**
 * Maps to test 1
 */
public class AddItemStoryTests extends AcceptanceTest {
    @Test
    public void addItemIfDoesntExist() throws ExecutionException, InterruptedException {
        getLogger().info("Story Test: Add Item");
        clerkLogin().thenApply(output -> now(input("create item", output.getState())))
                .thenApply(output -> {
                    assertEquals("Please Input Item Info:'ISBN'", output.getOutput());
                    assertEquals(ClientState.CREATEITEM, output.getState());;
                return now(input("9781442668584", output.getState()));
            }).thenAccept(output -> {
                assertEquals("Success!", output.getOutput());
                assertEquals(ClientState.CLERK, output.getState());
            }).get();
    }

    @Test
    public void addItemTitleDoesntExist() throws ExecutionException, InterruptedException {
            clerkLogin().thenApply(output -> now(input("create item", output.getState())))
            .thenApply(output -> {
                assertEquals("Please Input Item Info:'ISBN'", output.getOutput());
                assertEquals(ClientState.CREATEITEM, output.getState());
                return now(input("1234567891035", output.getState()));
            }).thenApply(output -> {
                assertEquals("The Title Does Not Exists! Would you like to add it? (y/n)", output.getOutput());
                assertEquals(ClientState.CONFIRM_ADD_TITLE, output.getState());
                return now(input("y", output.getState()));
            }).thenApply(output -> {
                assertEquals("Confirmed!", output.getOutput());
                assertEquals(ClientState.CREATETITLE, output.getState());
                return now(input("1234567891035,Timothy Goes To Hell", output.getState()));
            }).thenAccept(output -> {
                assertEquals("Success!", output.getOutput());
                assertEquals(ClientState.CLERK, output.getState());
            });
    }
}

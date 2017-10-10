import main.java.server.io.handler.model.ClientState;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;

public class AddTitleStoryTests extends AcceptanceTest {
    @Test
    public void addsTitleIfDoesNotExist() throws ExecutionException, InterruptedException {
        scenario("addsTitleIfDoesNotExist");
        clerkLogin().thenApply(o -> now(input("create title", o.getState())))
            .thenApply(output -> {
                assertEquals("Please Input Title Info:'ISBN,title'", output.getOutput());
                assertEquals(ClientState.CREATETITLE, output.getState());
                return now(input("1357908641395,lol", output.getState()));
            }).thenAccept(output -> {
                assertEquals("Success!", output.getOutput());
                assertEquals(ClientState.CLERK, output.getState());
        }).get();
    }
    // If matching title information exists, title will not be added.
    @Test
    public void matchingTitleDoesntAdd() throws ExecutionException, InterruptedException {
        scenario("matchingTitleDoesntAdd");
        clerkLogin().thenApply(o -> now(input("create title", o.getState())))
            .thenApply(output -> {
                assertEquals("Please Input Title Info:'ISBN,title'", output.getOutput());
                assertEquals(ClientState.CREATETITLE, output.getState());
                return now(input("9781442616899,Dante's lyric poetry ", output.getState()));
            }).thenAccept(output -> {
                assertEquals("The Title Already Exists!", output.getOutput());
                assertEquals(ClientState.CLERK, output.getState());
        }).get();
    }
}

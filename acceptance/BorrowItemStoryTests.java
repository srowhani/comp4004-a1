import main.java.server.io.handler.model.ClientState;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

import static main.java.server.io.handler.model.ClientState.CLERK;
import static main.java.server.io.handler.model.ClientState.CREATEUSER;
import static org.junit.Assert.assertEquals;

public class BorrowItemStoryTests extends AcceptanceTest {
    //    If user is allowed, the loan is created to represent action of borrowing.
    @Test
    public void borrowItemIfUserIsAllowed () throws ExecutionException, InterruptedException {
        String csvUsernameIsbnCopyNumber = "";
        clerkLogin()
        .thenApply(output -> now(input("create user", output.getState())))
        .thenApply(output -> {
            assertEquals("Please Input User Info:'username,password'", output.getOutput());
            assertEquals(CREATEUSER, output.getState());
            return now(input("test-user-987,test-password-987", output.getState()));
        })
        .thenAccept(output -> {
            assertEquals("Success!", output.getOutput());
            assertEquals(CLERK, output.getState());
        }).get();


        clerkLogin().thenApply(output -> now(input("create item", output.getState())))
                .thenApply(output -> {
                    assertEquals("Please Input Item Info:'ISBN'", output.getOutput());
                    assertEquals(ClientState.CREATEITEM, output.getState());;
                    return now(input("9781442668584", output.getState()));
                }).thenAccept(output -> {
            assertEquals(ClientState.CLERK, output.getState());
        }).get();

        clerkLogin()
        .thenApply(output -> now(input("borrow", output.getState())))
        .thenApply(output -> now(input("test-user-987,9781442668584,0", output.getState())))
        .thenAccept(output -> {
            assertEquals(CLERK, output.getState());
            assertEquals("Success!", output.getOutput());
        }).get();
    }
}

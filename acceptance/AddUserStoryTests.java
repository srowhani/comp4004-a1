import org.junit.Test;

import java.util.concurrent.ExecutionException;

import static main.java.server.io.handler.model.ClientState.CLERK;
import static main.java.server.io.handler.model.ClientState.CREATEUSER;
import static org.junit.Assert.assertEquals;

public class AddUserStoryTests extends AcceptanceTest {
    // If user doesn’t exist, user is added to the system.
    @Test
    public void addUserTest () throws ExecutionException, InterruptedException {
        clerkLogin()
        .thenApply(output -> now(input("create user", output.getState())))
        .thenApply(output -> {
            assertEquals("Please Input User Info:'username,password'", output.getOutput());
            assertEquals(CREATEUSER, output.getState());
            return now(input("test-user-12345,test-password-12345", output.getState()));
        })
        .thenAccept(output -> {
            assertEquals("Success!", output.getOutput());
            assertEquals(CLERK, output.getState());
        }).get();

    }
    //    If user does exist, librarian won’t be allowed to add them.
    @Test
    public void addAlreadyExistingUserTest () throws ExecutionException, InterruptedException {
        clerkLogin()
            .thenApply(output -> now(input("create user", output.getState())))
            .thenApply(output -> {
                assertEquals("Please Input User Info:'username,password'", output.getOutput());
                assertEquals(CREATEUSER, output.getState());
                return now(input("test-user-1234,test-password-1234", output.getState()));
            })
            .thenAccept(output -> {
                assertEquals("Success!", output.getOutput());
                assertEquals(CLERK, output.getState());
            }).get();

        clerkLogin()
            .thenApply(output -> now(input("create user", output.getState())))
            .thenApply(output -> {
                assertEquals("Please Input User Info:'username,password'", output.getOutput());
                assertEquals(CREATEUSER, output.getState());
                return now(input("test-user-1234,test-password-1234", output.getState()));
            })
            .thenAccept(output -> {
                assertEquals("The User Already Exists!", output.getOutput());
                assertEquals(CLERK, output.getState());
            }).get();
    }
}

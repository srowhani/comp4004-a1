import main.java.server.io.handler.model.ClientState;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class PayFineStoryTests extends AcceptanceTest {
    // If user privilege is revoked, the user’s privileges is returned.
    @Test
    public void userPrivilege () throws ExecutionException, InterruptedException {
        // create user
        clerkLogin()
        .thenApply(clerkLoginOutput -> now(input("create user", clerkLoginOutput.getState())))
        .thenApply(beginCreateUser -> now(input("test-user-987654321,pass", beginCreateUser.getState())))
        .get();

        clerkLogin().thenApply(output -> now(input("create item", output.getState())))
                .thenApply(output -> {
                    assertEquals("Please Input Item Info:'ISBN'", output.getOutput());
                    assertEquals(ClientState.CREATEITEM, output.getState());;
                    return now(input("9781442668584", output.getState()));
                }).thenAccept(output -> {
            assertEquals("Success!", output.getOutput());
            assertEquals(ClientState.CLERK, output.getState());
        }).get();
        // create loan
        input("borrow", ClientState.USER)
        .thenApply(borrowMenuOutput -> now(input("test-user-987654321,9781442668584,0", borrowMenuOutput.getState())))
        .thenApply(borrowResultOutput -> {
            assertEquals("Success!", borrowResultOutput.getOutput());
            return now(input("return", borrowResultOutput.getState()));
        }) // pay loan on later date
        .thenAccept(payFineMenu -> now(input("test-user-987654321,9781442668584,0,10", payFineMenu.getState()))).get();
        // try to withdraw, and fail
        input("borrow", ClientState.USER)
        .thenApply(borrowMenuOutput -> now(input("test-user-987654321,9781442668584,1", borrowMenuOutput.getState())))
        .thenAccept(borrowResultOutput -> {
            assertEquals("Outstanding Fee Exists!", borrowResultOutput.getOutput());
        }).get();
    }

    @Test
    public void userAllowedToReturnItemsAfterPayingFine () {
        fail();
    }

    // User must be allowed to borrow books again.

}

import main.java.server.io.handler.model.ClientState;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class PayFineStoryTests extends AcceptanceTest {
    // If user privilege is revoked, the user’s privileges is returned.
    @Test
    public void userPrivilege () throws ExecutionException, InterruptedException {
        scenario("userPrivilege");
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
        .thenAccept(returnItemMenu -> now(input("test-user-987654321,9781442668584,0,10", returnItemMenu.getState()))).get();
        // try to withdraw, and fail
        input("borrow", ClientState.USER)
        .thenApply(borrowMenuOutput -> now(input("test-user-987654321,9781442668584,1", borrowMenuOutput.getState())))
        .thenAccept(borrowResultOutput -> {
            assertEquals("Outstanding Fee Exists!", borrowResultOutput.getOutput());
        }).get();
    }

    @Test
    public void userAllowedToBorrowItemsAfterPayingFine () throws ExecutionException, InterruptedException {
        scenario("userAllowedToBorrowItemsAfterPayingFine");
        clerkLogin()
        .thenApply(clerkLoginOutput -> now(input("create user", clerkLoginOutput.getState())))
        .thenApply(beginCreateUser -> now(input("test-user-99999,pass", beginCreateUser.getState())))
        .get();

        input("pay fine", ClientState.USER)
        .thenApply(payFineMenu -> now(input("test-user-99999", payFineMenu.getState())))
        .get();

        input("borrow", ClientState.USER)
        .thenApply(borrowMenuOutput -> now(input("test-user-99999,9781442668584,0", borrowMenuOutput.getState())))
        .thenApply(borrowResultOutput -> now(input("return", ClientState.USER)))
        .thenApply(borrowResultOutput -> now(input("test-user-99999,9781442668584,0,10", borrowResultOutput.getState())))
        .get();

        input("pay fine", ClientState.USER)
        .thenApply(payFineMenu -> now(input("test-user-99999", payFineMenu.getState())))
        .get();

        input("borrow", ClientState.USER)
        .thenApply(borrowMenuOutput -> now(input("test-user-99999,9781442668584,0", borrowMenuOutput.getState())))
        .thenAccept(borrowResultOutput -> {
            assertEquals("Success!", borrowResultOutput.getOutput());
        }).get();
    }
}

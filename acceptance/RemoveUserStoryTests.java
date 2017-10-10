import org.junit.Test;

import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;

public class RemoveUserStoryTests extends AcceptanceTest {
    // If user has an outstanding loan, they cannot be removed from the system.
    @Test
    public void attemptRemoveUserWhenOutstandingLoanExists () throws ExecutionException, InterruptedException {
        scenario("attemptRemoveUserWhenOutstandingLoanExists");
        String username = "bob-1";
        String isbn = "9999999999997";

        createUser(username, "pass").get();
        createTitle(isbn, "bob-1 goes to school").get();
        addItem(isbn).get();
        borrowItem(username, isbn, "0").get();
        deleteUser(username).thenAccept(result -> {
            assertEquals("Outstanding Loan Exists!", result.getOutput());
        }).get();
    }
    // If the user is free of loans, they can successfully be removed.

    @Test
    public void canRemoveUserSuccessfully () throws ExecutionException, InterruptedException {
        scenario("canRemoveUserSuccessfully");
        String username = "bob-2";
        createUser(username, "pass").get();
        deleteUser(username).thenAccept(this::assertSuccess).get();
    }

}

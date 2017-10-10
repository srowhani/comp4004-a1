import org.junit.Test;

import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;

public class RenewLoanStoryTests extends AcceptanceTest {
    // If user has outstanding fee, loan cannot be renewed.
    @Test
    public void userHasOutstandingFeeLoanCannotBeRenewed () throws ExecutionException, InterruptedException {
        scenario("userHasOutstandingFeeLoanCannotBeRenewed");

        String username = "tom-1";
        String isbn = "1111111111111";

        createUser(username, "pass").get();
        createTitle(isbn, "xDDD").get();
        addItem(isbn).get();
        borrowItem(username, isbn, "0").get();
        returnItemLater(username, isbn, "0", 100).get();
        renewItem(username, isbn, "0").thenAccept(result -> {
            assertEquals("Outstanding Fee Exists!", result.getOutput());
        }).get();
    }
    // If loan has already been renewed, the librarian is not allowed to renew it.
    @Test
    public void cantRenewAlreadyRenewedLoan () throws ExecutionException, InterruptedException {
        scenario("cantRenewAlreadyRenewedLoan");

        String username = "tom-2";
        String isbn = "1111111111112";

        createUser(username, "pass").get();
        createTitle(isbn, "tom-2: The return of the tom").get();
        addItem(isbn).get();
        borrowItem(username, isbn, "0").get();
        renewItem(username, isbn, "0").thenAccept(this::assertSuccess).get();

        renewItem(username, isbn, "0").thenAccept(result -> {
            assertEquals("Item Already Renewed!", result.getOutput());
        }).get();
    }
}

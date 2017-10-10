import main.java.server.io.dao.LoanTable;
import main.java.util.Config;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class ReturnItemStoryTests extends AcceptanceTest {
    // If loan has been overdue for more than three days, the user tied to the loan will have privileges revoked.
    @Test
    public void revokesUserPrivilegesForOverdueLoan () throws ExecutionException, InterruptedException {
        String username = "test-user-test";
        String isbn = "6666666666666";
        createUser(username, "password").get();
        createTitle(isbn, "Devils book").get();
        addItem(isbn).get();
        borrowItem(username, isbn, "0").get();
        returnItemLater(username, isbn, "0", Config.OVERDUE + 3).thenAccept(this::assertSuccess).get();
        addItem(isbn).get();
        borrowItem(username, isbn, "1").thenAccept(result -> {
            assertEquals("Outstanding Fee Exists!", result.getOutput());
        }).get();
    }
    // If loan is not overdue, loan entity is destroyed from list.
    @Test
    public void deletesLoanCorrectly () throws ExecutionException, InterruptedException {
        String username = "test-user-test-2";
        String isbn = "6666666666665";
        createUser(username, "pass").get();
        createTitle(isbn, "Devils book: 2").get();
        addItem(isbn).get();
        borrowItem(username, isbn, "0").get();
        returnItemLater(username, isbn, "0", Config.OVERDUE - 1).thenAccept(this::assertSuccess).get();
        boolean hasLoanThatFitsCriteria = LoanTable.getInstance().checkLoan(loanEntity -> loanEntity.getISBN().equals(isbn));

        assertFalse(hasLoanThatFitsCriteria);
    }

}

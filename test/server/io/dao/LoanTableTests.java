package server.io.dao;

import org.junit.Test;
import server.io.model.LoanEntity;

import java.util.Date;
import java.util.Optional;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.fail;
import static lib.Assert.assertDoesNotThrow;

public class LoanTableTests {
    LoanEntity testLoan;
    @Test
    public void getInstanceNonNull () {
        assertDoesNotThrow(() -> LoanTable.getInstance());
    }

    @Test
    public void createLoan () {
        LoanTable loanTable = LoanTable.getInstance();
        assertNotNull(testLoan = loanTable.createLoan(1, "", "",  new Date()));
    }

    @Test
    public void testLookup () {
        LoanTable loanTable = LoanTable.getInstance();
        createLoan();
        Optional<LoanEntity> loanEntityOptional = loanTable.lookup(
                loan -> loan.getISBN().equals(testLoan.getISBN()));
    }
}

package server.io.dao;

import org.junit.Test;

import java.util.Date;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.fail;
import static lib.Assert.assertDoesNotThrow;

public class LoanTableTests {
    @Test
    public void getInstanceNonNull () {
        assertDoesNotThrow(() -> LoanTable.getInstance());
    }

    @Test
    public void createLoan () {
        LoanTable loanTable = LoanTable.getInstance();
        assertNotNull(loanTable.createLoan(1, "", "",  new Date()));
    }

    @Test
    public void testLookup () {
        LoanTable loanTable = LoanTable.getInstance();
        fail("Not yet implemented :(");
    }
}

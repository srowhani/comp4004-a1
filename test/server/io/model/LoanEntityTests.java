package server.io.model;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class LoanEntityTests implements ModelTest {

    @Override
    public void canInstantiate() {
        assertNotNull(
                new LoanEntity(1, "isbn", "copyNumber", new Date(), "renewState"));
    }

    @Override
    public void canAccessProperties() {
        int uid = 1;
        String isbn = "isbn",
               copyNumber = "copyNumber",
               renewState = "renewState";

        Date date = new Date();
        LoanEntity loanEntity = new LoanEntity(uid, isbn, copyNumber, date, renewState);

        assertEquals(loanEntity.getISBN(), isbn);
        assertEquals(loanEntity.getCopyNumber(), copyNumber);
        assertEquals(renewState.getRenewState(), renewState);

        assertEquals(loanEntity.getUserId(), uid);
        assertEquals(loanEntity.getDate(), date);
    }
}

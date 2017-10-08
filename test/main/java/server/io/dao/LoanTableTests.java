package main.java.server.io.dao;

import main.java.server.io.error.*;
import main.java.server.io.model.LoanEntity;
import main.java.server.io.model.UserEntity;
import org.junit.Test;

import java.util.Date;
import java.util.Optional;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.apache.log4j.helpers.LogLog.warn;
import static org.junit.Assert.fail;
import static util.Assert.assertDoesNotThrow;

public class LoanTableTests {
    @Test
    public void getInstanceNonNull() {
        assertDoesNotThrow(() -> LoanTable.getInstance());
    }

    @Test
    public void createLoan() {
        LoanTable loanTable = LoanTable.getInstance();
        try {
            TitleTable.getInstance().createTitle("1234567891237", "Timothy joins the army!");
        } catch (Exception e) {
            warn(e.getMessage());
        }

        try {
            ItemTable.getInstance().addItem("1234567891237");
        } catch (NoSuchISBNExistsException e) {
            warn(e.getMessage());
        }

        UserEntity entity = null;

        try {
            entity = UserTable.getInstance().add("John", "Doe");
        } catch (UserEntityExistsException e) {
            entity = UserTable.getInstance().lookup(user -> user.getUsername().equals("John")).get();
        }

        try {
            loanTable.createLoan(entity.getId(), "1234567891237", "0", new Date());
        } catch (ItemNotAvailableException e) {
            warn(e.getMessage());
        } catch (Exception e) {
            fail(e.getClass().toString() + "-" +  e.getMessage());
        }
    }

    @Test
    public void testLookup() {
        LoanTable loanTable = LoanTable.getInstance();
        createLoan();
        Optional<LoanEntity> loanEntityOptional = loanTable.lookup(
                loan -> loan.getISBN().equals("1234567891237"));
        assertTrue(loanEntityOptional.isPresent());
    }
}

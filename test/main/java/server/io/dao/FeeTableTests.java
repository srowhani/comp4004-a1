package main.java.server.io.dao;

import main.java.server.io.error.PendingLoansExistException;
import main.java.server.io.error.UserEntityExistsException;
import main.java.server.io.model.UserEntity;
import main.java.util.Config;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static junit.framework.TestCase.assertTrue;
import static org.apache.log4j.helpers.LogLog.warn;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static util.Assert.assertDoesNotThrow;

public class FeeTableTests {
    FeeTable feeTable;


    @Before
    public void setup() {
        feeTable = FeeTable.getInstance();
        feeTable.getFeeTable().clear();
        UserTable.getInstance().getUserTable().clear();
    }

    @Test
    public void instantiateInstance() {
        assertDoesNotThrow(() -> FeeTable.getInstance());
    }

    @Test
    public void lookupExistingItemReturnsPresentOptional() {
        UserEntity userEntity = null;
        try {
            userEntity = UserTable.getInstance().add("test-user", "password");
        } catch (UserEntityExistsException e) {
            userEntity = UserTable.getInstance().lookup(user -> user.getUsername().equals("test-user")).get();
        }
        int daysOverDue = 3;
        feeTable.applyFee(userEntity.getId(), Config.SIMULATED_DAY * (Config.OVERDUE + daysOverDue));

        assertTrue(feeTable.lookup(feeEntity -> feeEntity.getFee() == daysOverDue).isPresent());
    }

    @Test
    public void lookupExistingItemReturnsEmptyOptional() {
        assertNotNull(feeTable.lookup(item -> false));
    }

    @Test
    public void applyFeeAddsFeeToUserCorrectly() {
        UserEntity userEntity = null;
        try {
            userEntity = UserTable.getInstance().add("test-user", "password");
        } catch (UserEntityExistsException e) {
            userEntity = UserTable.getInstance().lookup(user -> user.getUsername().equals("test-user")).get();
        }
        int daysOverDue = 3;

        int id = userEntity.getId();
        feeTable.applyFee(id, Config.SIMULATED_DAY * (Config.OVERDUE + daysOverDue));

        assertEquals(daysOverDue, feeTable.lookupFee(id));
    }

    @Test
    public void payFineAdjustsAmountOwedCorrectly() {
        UserEntity userEntity = null;
        try {
            userEntity = UserTable.getInstance().add("test-user", "password");
        } catch (UserEntityExistsException e) {
            userEntity = UserTable.getInstance().lookup(user -> user.getUsername().equals("test-user")).get();
        }

        applyFeeAddsFeeToUserCorrectly();

        try {
            feeTable.payFine(userEntity.getId());
        } catch (PendingLoansExistException e) {
            warn(e.getMessage());
        }

        assertEquals(0, feeTable.lookupFee(userEntity.getId()));
        assertEquals(0, feeTable.lookupFee(123));

    }
}

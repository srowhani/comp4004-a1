package main.java.server.io.dao;

import main.java.server.io.model.UserEntity;
import main.java.util.Config;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static junit.framework.TestCase.assertTrue;
import static util.Assert.assertDoesNotThrow;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class FeeTableTests {
    FeeTable feeTable;


    @Before
    public void setup () {
        feeTable = FeeTable.getInstance();
        feeTable.getFeeTable().clear();

    }

    @Test
    public void instantiateInstance () {
        assertDoesNotThrow(() -> FeeTable.getInstance());
    }

    @Test
    public void lookupExistingItemReturnsPresentOptional () {
        Optional<UserEntity> existingUser = UserTable.getInstance().getUserTable().stream().findAny();
        assertTrue(existingUser.isPresent());
        int daysOverDue = 3;
        feeTable.applyFee(existingUser.get().getId(), Config.SIMULATED_DAY * (Config.OVERDUE + daysOverDue) );

        assertTrue(feeTable.lookup(feeEntity -> feeEntity.getFee() == daysOverDue).isPresent());
    }

    @Test
    public void lookupExistingItemReturnsEmptyOptional () {
        assertNotNull(feeTable.lookup(item -> false));
    }

    @Test
    public void applyFeeAddsFeeToUserCorrectly () {

        Optional<UserEntity> existingUser = UserTable.getInstance().getUserTable().stream().findFirst();

        assertTrue(existingUser.isPresent());
        int daysOverDue = 3;

        int id = existingUser.get().getId();
        feeTable.applyFee(id, Config.SIMULATED_DAY * (Config.OVERDUE + daysOverDue));

        assertEquals(daysOverDue, feeTable.lookupFee(id));
    }

    @Test
    public void payFineAdjustsAmountOwedCorrectly () {
        Optional<UserEntity> existingUser = UserTable.getInstance().getUserTable().stream().findFirst();
        assertTrue(existingUser.isPresent());

        applyFeeAddsFeeToUserCorrectly();

        feeTable.payFine(existingUser.get().getId());

        assertEquals(0, feeTable.lookupFee(existingUser.get().getId()));
        assertEquals(0, feeTable.lookupFee(123));

    }
}

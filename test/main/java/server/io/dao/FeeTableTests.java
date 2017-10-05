package main.java.server.io.dao;

import main.java.server.io.model.UserEntity;
import main.java.util.Config;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.Optional;

import static junit.framework.TestCase.assertTrue;
import static lib.Assert.assertDoesNotThrow;
import static org.junit.Assert.assertEquals;
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

        if (existingUser.isPresent()) {

        }
    }

    @Test
    public void lookupExistingItemReturnsEmptyOptional () {
        fail("Not yet implemented");
    }

    @Test
    public void checkUserAssertsUserHasNoFees () {
        fail("Not yet implemented");
    }

    @Test
    public void lookupFeeReturnsCorrectAmountOwedByUserEntity () {
        fail("Not yet implemented");
    }

    @Test
    public void applyFeeAddsFeeToUserCorrectly () {

        Optional<UserEntity> existingUser = UserTable.getInstance().getUserTable().stream().findAny();

        assertTrue(existingUser.isPresent());
        int daysOverDue = 3;

        int id = existingUser.get().getId();
        feeTable.applyFee(id, Config.SIMULATED_DAY * (Config.OVERDUE + daysOverDue));

        assertEquals(daysOverDue, feeTable.lookupfee(id));
    }

    @Test
    public void payFineAdjustsAmountOwedCorrectly () {
        fail("Not yet impl");
    }
}

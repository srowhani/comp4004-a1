package main.java.server.io.dao;

import org.junit.Test;

import static lib.Assert.assertDoesNotThrow;
import static org.junit.Assert.fail;

public class FeeTableTests {
    @Test
    public void instantiateInstance () {
        assertDoesNotThrow(() -> FeeTable.getInstance());
    }

    @Test
    public void lookupExistingItemReturnsPresentOptional () {
        fail("Not yet implemented");
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
        fail("Not yet implemented");
    }

    @Test
    public void payFineAdjustsAmountOwedCorrectly () {
        fail("Not yet impl");
    }
}

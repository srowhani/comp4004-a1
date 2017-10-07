package main.java.server.io.model;

import org.junit.Test;

import static util.Assert.assertDoesNotThrow;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class FeeEntityTests implements GenericModelTest {
    @Override
    @Test
    public void canInstantiate() throws Exception {
        assertDoesNotThrow(() -> new FeeEntity());
        assertDoesNotThrow(() -> new FeeEntity(1, 1));
    }

    @Override
    @Test
    public void canAccessProperties() {
        FeeEntity feeEntity = new FeeEntity();

        int userId = 1,
            fee = 1;

        feeEntity.setUserId(userId);
        feeEntity.setFee(fee);

        assertEquals(feeEntity.getUserId(), userId);
        assertEquals(feeEntity.getFee(), fee);
    }
}

package server.io.model;

import org.junit.Test;

import static lib.Assert.assertDoesNotThrow;
import static org.junit.Assert.fail;

public class ItemEntityTests implements ModelTest {
    @Override
    @Test
    public void canInstantiate() throws Exception {
        assertDoesNotThrow(() -> {
            ItemEntity itemEntity = new ItemEntity(1, "ISBN", "copyNumber");
        });
    }

    @Override
    @Test
    public void canAccessProperties() {
        fail("Not yet implemented :(");
    }
}

package server.io.model;

import org.junit.Test;

import static lib.Assert.assertDoesNotThrow;
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
        fail("Not yet implem");
    }
}

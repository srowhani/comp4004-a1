package main.java.server.io.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static util.Assert.assertDoesNotThrow;

public class ItemEntityTests implements GenericModelTest {
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
        ItemEntity itemEntity = new ItemEntity();

        int itemId = 1;
        String isbn = "isbn";
        String copyNumber = "copyNumber";

        itemEntity.setItemId(itemId);
        itemEntity.setISBN(isbn);
        itemEntity.setCopyNumber(copyNumber);

        assertEquals(itemEntity.getItemId(), itemId);
        assertEquals(itemEntity.getISBN(), isbn);
        assertEquals(itemEntity.getCopyNumber(), copyNumber);

    }
}

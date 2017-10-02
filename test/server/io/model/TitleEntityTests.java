package server.io.model;

import org.junit.Test;

import static lib.Assert.assertDoesNotThrow;
import static org.junit.Assert.assertEquals;

public class TitleEntityTests implements ModelTest {
    @Override
    @Test
    public void canInstantiate () {
        assertDoesNotThrow(() -> new TitleEntity("ISBN", "Title"));
    }
    @Override
    @Test
    public void canAccessProperties () {
        String isbn = "ISBN";
        String title = "Title";

        TitleEntity titleEntity = new TitleEntity(isbn, title);

        assertEquals(titleEntity.getISBN(), isbn);
        assertEquals(titleEntity.getBooktitle(), title);
    }
}

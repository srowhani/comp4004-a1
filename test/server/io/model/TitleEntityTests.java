package server.io.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TitleEntityTests implements ModelTest {

    @Test
    public void canInstantiate () {
        assertNotNull(new TitleEntity("ISBN", "Title"));
    }

    @Test
    public void canAccessProperties () {
        String isbn = "ISBN";
        String title = "Title";

        TitleEntity titleEntity = new TitleEntity(isbn, title);

        assertEquals(titleEntity.getISBN(), isbn);
        assertEquals(titleEntity.getBooktitle(), title);
    }
}

package server.io.dao;

import org.junit.Test;
import server.io.model.TitleEntity;

import java.util.Optional;
import java.util.stream.Collectors;

import static lib.Assert.assertDoesNotThrow;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class TitleTableTests {
    @Test
    public void getInstanceNonNull () {
        assertDoesNotThrow(() -> TitleTable.getInstance());
    }

    @Test
    public void createTitle () {
        TitleTable titleTable = TitleTable.getInstance();
        String isbn = "ISBN";
        String title = "Book title";
        titleTable.createTitle(isbn, title);

        assertTrue(titleTable.getTitleTable().stream().anyMatch(item ->
                item.getISBN().equals(isbn) && item.getBooktitle().equals(title)
        ));

    }

    @Test
    public void deleteTitle () {
        TitleTable titleTable = TitleTable.getInstance();

        titleTable.getTitleTable()
                .stream()
                .map(u -> u.getISBN())
                .collect(Collectors.toList())
                .forEach(id -> titleTable.remove(id));

        assertEquals(titleTable.getTitleTable().size(), 0);
    }

    @Test
    public void lookupRetrievesOptional () {
        createTitle();
        TitleTable titleTable = TitleTable.getInstance();

        TitleEntity titleEntity = titleTable.getTitleTable().get(0);

        Optional<TitleEntity> titleEntityOptional = titleTable.lookup(title -> title.getISBN() == titleEntity.getISBN());
        assertTrue(titleEntityOptional.isPresent());
        assertEquals(titleEntityOptional.get(), titleEntity);

        titleEntityOptional = titleTable.lookup(title -> title.getBooktitle() == titleEntity.getBooktitle());
        assertTrue(titleEntityOptional.isPresent());
        assertEquals(titleEntityOptional.get(), titleEntity);
    }
}

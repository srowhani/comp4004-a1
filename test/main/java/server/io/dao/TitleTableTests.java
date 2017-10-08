package main.java.server.io.dao;

import main.java.server.io.error.TitleEntityExistsException;
import main.java.server.io.error.TitleEntityNotFoundException;
import main.java.server.io.model.TitleEntity;
import org.junit.Test;

import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
import static util.Assert.assertDoesNotThrow;

public class TitleTableTests {
    @Test
    public void getInstanceNonNull() {
        assertDoesNotThrow(() -> TitleTable.getInstance());
    }

    @Test
    public void createTitle() {
        TitleTable titleTable = TitleTable.getInstance();
        String isbn = "ISBN";
        String title = "Book title";
        try {
            titleTable.createTitle(isbn, title);
        } catch (TitleEntityExistsException e) {
            fail(e.getMessage());
        }

        assertTrue(titleTable.getTitleTable().stream().anyMatch(item ->
                item.getISBN().equals(isbn) && item.getBooktitle().equals(title)
        ));

    }

    @Test
    public void deleteTitle() {
        TitleTable titleTable = TitleTable.getInstance();

        titleTable.getTitleTable()
                .stream()
                .map(u -> u.getISBN())
                .collect(Collectors.toList())
                .forEach(id -> {
                    try {
                        titleTable.remove(id);
                    } catch (TitleEntityNotFoundException e) {
                        fail(e.getMessage());
                    }
                });

        assertEquals(titleTable.getTitleTable().size(), 0);
    }

    @Test
    public void lookupRetrievesOptional() {
        createTitle();
        TitleTable titleTable = TitleTable.getInstance();

        TitleEntity titleEntity = titleTable.getTitleTable().get(0);

        Optional<TitleEntity> titleEntityOptional = titleTable.lookup(title -> title.getISBN().equals(titleEntity.getISBN()));
        assertTrue(titleEntityOptional.isPresent());
        assertEquals(titleEntityOptional.get(), titleEntity);

        titleEntityOptional = titleTable.lookup(title -> title.getBooktitle().equals(titleEntity.getBooktitle()));
        assertTrue(titleEntityOptional.isPresent());
        assertEquals(titleEntityOptional.get(), titleEntity);
    }
}

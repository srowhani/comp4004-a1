package main.java.server.io.dao;

import main.java.server.io.error.ItemEntityNotFoundException;
import main.java.server.io.error.LoanExistsException;
import main.java.server.io.error.NoSuchISBNExistsException;
import main.java.server.io.error.TitleEntityExistsException;
import main.java.server.io.model.ItemEntity;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.apache.log4j.helpers.LogLog.warn;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;
import static util.Assert.assertDoesNotThrow;

public class ItemTableTests {
    @Test
    public void getInstanceNonNull() {
        assertDoesNotThrow(() -> ItemTable.getInstance());
    }

    @Test
    public void addItemAndPerformLookup() {
        ItemTable itemTable = ItemTable.getInstance();
        String isbn = "9781442668584";
        itemTable.getItemTable().clear();

        try {
            TitleTable.getInstance().createTitle(isbn, "Foobar: Return of the bar");
        } catch (TitleEntityExistsException e) {
            warn(e.getMessage());
        }

        try {
            itemTable.addItem(isbn);
        } catch (NoSuchISBNExistsException e) {
            warn(e.getMessage());
        }

        assertTrue(TitleTable.getInstance().lookup(title -> title.getISBN().equals(isbn)).isPresent());
        assertTrue(itemTable.lookup(item -> item.getISBN().equals(isbn)).isPresent());
    }

    @Test
    public void testLookupOnNonExistentISBN() {
        ItemTable itemTable = ItemTable.getInstance();
        assertFalse(itemTable.lookup(item -> item.getISBN().equals("xD")).isPresent());
    }

    @Test
    public void deleteAllDestroysAllItems() {
        ItemTable itemTable = ItemTable.getInstance();
        itemTable.getItemTable().clear();
        addItemAndPerformLookup();
        ItemEntity itemEntity = itemTable.getItemTable().get(0);
        itemTable.deleteAll(itemEntity.getISBN());
        assertTrue(itemTable.getItemTable().stream().noneMatch(item -> item.getISBN().equals(itemEntity.getISBN())));
    }

    @Test
    public void deleteItem() {
        addItemAndPerformLookup();

        ItemTable itemTable = ItemTable.getInstance();
        ItemEntity itemEntity = itemTable.getItemTable().get(0);

        try {
            itemTable.delete(itemEntity.getISBN(), itemEntity.getCopyNumber());
        } catch (ItemEntityNotFoundException e) {
            e.printStackTrace();
        } catch (LoanExistsException e) {
            e.printStackTrace();
        }

        assertTrue(!itemTable.lookup(item -> item.getISBN().equals(itemEntity.getISBN())).isPresent());
    }
}

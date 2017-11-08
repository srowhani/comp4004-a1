package main.java.server.io.dao;

import main.java.server.io.error.ItemEntityNotFoundException;
import main.java.server.io.error.LoanExistsException;
import main.java.server.io.error.NoSuchISBNExistsException;
import main.java.server.io.model.ItemEntity;
import main.java.util.Trace;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class ItemTable {
    private Logger logger = Trace.getInstance().getLogger("operation_file");
    private List<ItemEntity> itemList = new ArrayList<>();

    private static class ItemListHolder {
        private static final ItemTable INSTANCE = new ItemTable();
    }

    private ItemTable() {

        logger.info(String.format("Operation:Initialize ItemTable;ItemTable: %s", itemList));
    }

    public static ItemTable getInstance() {
        return ItemListHolder.INSTANCE;
    }

    public ItemEntity addItem(String isbn) throws NoSuchISBNExistsException {
        if (!TitleTable.getInstance().lookup(title -> title.getISBN().equals(isbn)).isPresent()) {
            logger.info(String.format("Operation:Create New Item;Item Info:[%s,%s];State:Fail;Reason:No such ISBN existed.", isbn, "N/A"));
            throw new NoSuchISBNExistsException();
        }

        String copyNumber = String.valueOf(itemList
                .stream()
                .filter(item -> item.getISBN().equals(isbn))
                .count());

        ItemEntity itemEntity = new ItemEntity(itemList.size(), isbn, copyNumber);
        itemList.add(itemEntity);

        logger.info(String.format("Operation:Create New Item;Item Info:[%s,%s];State:Success", isbn, copyNumber));

        return itemEntity;
    }

    public Optional<ItemEntity> lookup(Predicate<ItemEntity> itemEntityPredicate) {
        return itemList
                .stream()
                .filter(itemEntityPredicate)
                .findFirst();
    }

    public ItemEntity delete(String isbn, String copyNumber) throws ItemEntityNotFoundException, LoanExistsException {
        Optional<ItemEntity> itemEntityOptional = itemList
                .stream()
                .filter(item -> item.getISBN().equals(isbn) && item.getCopyNumber().equals(copyNumber))
                .findFirst();

        if (!itemEntityOptional.isPresent()) {
            logger.info(String.format("Operation:Delete Item;Item Info:[%s,%s];State:Fail;Reason:The Item Does Not Exist.", isbn, copyNumber));
            throw new ItemEntityNotFoundException();
        }

        ItemEntity itemEntity = itemEntityOptional.get();

        boolean hasOutstandingLoan = LoanTable.getInstance().checkLoan(loanEntity ->
                loanEntity.getISBN().equals(isbn) && loanEntity.getCopyNumber().equals(copyNumber)
        );

        if (hasOutstandingLoan) {
            logger.info(String.format("Operation:Delete Item;Item Info:[%s,%s];State:Fail;Reason:The item is currently on loan.", isbn, copyNumber));
            throw new LoanExistsException();
        }

        logger.info(String.format("Operation:Delete Item;Item Info:[%s,%s];State:Success", isbn, "N/A"));

        itemList.remove(itemEntity);
        return itemEntity;
    }

    public void deleteAll(String isbn) {
        itemList.removeIf(item -> item.getISBN().equals(isbn));
    }

    public List<ItemEntity> getItemTable() {
        return itemList;
    }
}
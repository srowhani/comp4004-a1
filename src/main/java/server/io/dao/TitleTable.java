package main.java.server.io.dao;

import main.java.server.io.error.LoanExistsException;
import main.java.server.io.error.TitleEntityExistsException;
import main.java.server.io.error.TitleEntityNotFoundException;
import main.java.server.io.error.TitleInstanceExistsException;
import main.java.server.io.model.TitleEntity;
import main.java.util.Trace;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class TitleTable {
    private Logger logger = Trace.getInstance().getLogger("operation_file");
    List<TitleEntity> titleList = new ArrayList<TitleEntity>();

    private static class TitleListHolder {
        private static final TitleTable INSTANCE = new TitleTable();
    }

    private TitleTable() {
        logger.info(String.format("Operation:Initialize TitleTable;TitleTable: %s", titleList));
    }

    ;

    public static TitleTable getInstance() {
        return TitleListHolder.INSTANCE;
    }

    public TitleEntity createTitle(String isbn, String bookTitle) throws TitleEntityExistsException {
        int flag = 0;
        if (titleList.stream().anyMatch(title -> title.getISBN().equals(isbn))) {
            logger.info(String.format("Operation:Create New Title;Title Info:[%s,%s];State:Fail;Reason:The ISBN already existed.", isbn, bookTitle));
            throw new TitleEntityExistsException();
        }

        TitleEntity titleEntity = new TitleEntity(isbn, bookTitle);
        titleList.add(titleEntity);
        logger.info(String.format("Operation:Create New Title;Title Info:[%s,%s];State:Success", isbn, bookTitle));
        return titleEntity;
    }

    public Optional<TitleEntity> lookup(Predicate<TitleEntity> predicate) {
        return titleList
                .stream()
                .filter(predicate)
                .findFirst();
    }

    public TitleEntity remove(String isbn) throws TitleEntityNotFoundException, LoanExistsException, TitleInstanceExistsException {
        Optional<TitleEntity> title = titleList
                .stream()
                .filter(userEntity -> userEntity.getISBN().equals(isbn))
                .findFirst();

        if (LoanTable.getInstance().checkLoan(l -> l.getISBN().equals(isbn))) {
            throw new LoanExistsException();
        }

        if (!title.isPresent()) {
            throw new TitleEntityNotFoundException();
        }

        if (ItemTable.getInstance().lookup(item -> item.getISBN().equals(isbn)).isPresent()) {
            throw new TitleInstanceExistsException();
        }
        titleList.remove(title.get());
        return title.get();
    }

    public List<TitleEntity> getTitleTable() {
        return titleList;
    }
}

package main.java.server.io.dao;

import main.java.server.io.model.LoanEntity;
import main.java.server.io.model.TitleEntity;
import main.java.server.io.model.UserEntity;
import main.java.util.Trace;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class LoanTable {
    private Logger logger = Trace.getInstance().getLogger("operation_file");
    List<LoanEntity> loanList = new ArrayList<LoanEntity>();

    private static class LoanListHolder {
        private static final LoanTable INSTANCE = new LoanTable();
    }

    private LoanTable() {
        //set up the default list with some instances
        LoanEntity loan = new LoanEntity(0, "9781442668584", "1", new Date(), "0");
        loanList.add(loan);
        logger.info(String.format("Operation:Initialize LoanTable;LoanTable: %s", loanList));
    }

    public static LoanTable getInstance() {
        return LoanListHolder.INSTANCE;
    }

    public Optional<LoanEntity> lookup(Predicate<LoanEntity> loanEntityPredicate) {
        return loanList
                .stream()
                .filter(loanEntityPredicate)
                .findFirst();
    }

    public LoanEntity createLoan(int userId, String titleISBN, String copyNumber, Date date) {
        Optional<UserEntity> userEntity = UserTable.getInstance()
                .lookup(user -> user.getId() == userId);
        Optional<TitleEntity> titleEntity = TitleTable.getInstance()
                .lookup(title -> title.getISBN().equals(titleISBN));
        // boolean copynumber = ItemTable.getInstance().lookup(titleISBN, copyNumber);

        // TODO: Implement pulling of data from other tables
        // TODO: Possible refactor accessing DAO from Repository layer


        LoanEntity loanEntity = new LoanEntity(userId, titleISBN, copyNumber, date, "0");
        loanList.add(loanEntity);
        return loanEntity;
    }

    public boolean checkLimit(int j) {
        return false;
    }

    public Object renewal(int j, String string, String string2, Date date) {
        return null;
    }

    public Object returnItem(int j, String string, String string2, Date date) {
        return null;
    }

    public List<LoanEntity> getLoanTable() {
        return loanList;
    }

    /**
     * Verify user has no outstanding loans
     * If user has a loan, return true (indicating they cant pay)
     * Else return true
     *
     * @param userId
     */
    public boolean checkLoanByUserId(int userId) {
        return loanList.stream().anyMatch(loanEntity -> loanEntity.getUserId() == userId);
    }

    /**
     * If loan matches given predicate, return false
     *
     * @param loanEntityPredicate
     * @return
     */
    public boolean checkLoan(Predicate<LoanEntity> loanEntityPredicate) {
        return loanList.stream().anyMatch(loanEntityPredicate);
    }
}

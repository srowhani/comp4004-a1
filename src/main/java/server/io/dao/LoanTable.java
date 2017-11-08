package main.java.server.io.dao;

import main.java.server.io.error.*;
import main.java.server.io.model.LoanEntity;
import main.java.util.Config;
import main.java.util.Trace;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class LoanTable {
    private Logger logger = Trace.getInstance().getLogger("operation_file");
    private List<LoanEntity> loanList = new ArrayList<>();

    private static class LoanListHolder {
        private static final LoanTable INSTANCE = new LoanTable();
    }

    private LoanTable() {
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

    public void createLoan(int userId, String titleISBN, String copyNumber, Date date) throws MaximumBorrowedItemsExceededException, OutstandingFeeExistsException, NoSuchISBNExistsException, UserEntityNotFoundException, ItemEntityNotFoundException, ItemNotAvailableException {
        boolean user = UserTable.getInstance().lookup(userEntity -> userEntity.getId() == userId).isPresent();
        boolean isbn = TitleTable.getInstance().lookup(titleEntity -> titleEntity.getISBN().equals(titleISBN)).isPresent();
        boolean copynumber = ItemTable.getInstance().lookup(itemEntity ->
                itemEntity.getISBN().equals(titleISBN) && itemEntity.getCopyNumber().equals(copyNumber)).isPresent();
        boolean oloan = !LoanTable.getInstance().lookup(loanEntity ->
                loanEntity.getISBN().equals(titleISBN) && loanEntity.getCopyNumber().equals(copyNumber)
        ).isPresent();

        boolean limit = LoanTable.getInstance().checkLimit(userId);
        boolean fee = FeeTable.getInstance().lookupFee(userId) > 0;

        if (!user) {
            logger.info(String.format("Operation:Borrow Item;Loan Info:[%d,%s,%s,%s];State:Fail;Reason:Invalid User.", userId, titleISBN, copyNumber, date.toString()));
            throw new UserEntityNotFoundException();
        } else if (!isbn) {
            logger.info(String.format("Operation:Borrow Item;Loan Info:[%d,%s,%s,%s];State:Fail;Reason:Invalid ISBN.", userId, titleISBN, copyNumber, date.toString()));
            throw new NoSuchISBNExistsException();
        } else if (copynumber == false) {
            logger.info(String.format("Operation:Borrow Item;Loan Info:[%d,%s,%s,%s];State:Fail;Reason:Invalid Copynumber.", userId, titleISBN, copyNumber, date.toString()));
            throw new ItemEntityNotFoundException();
        } else {
            if (oloan) {
                if (limit && !fee) {
                    LoanEntity loan = new LoanEntity(userId, titleISBN, copyNumber, date, 0);
                    loanList.add(loan);
                    logger.info(String.format("Operation:Borrow Item;Loan Info:[%d,%s,%s,%s];State:Success", userId, titleISBN, copyNumber, date.toString()));
                } else if (limit == false) {
                    logger.info(String.format("Operation:Borrow Item;Loan Info:[%d,%s,%s,%s];State:Fail;Reason:The Maximun Number of Items is Reached.", userId, titleISBN, copyNumber, date.toString()));
                    throw new MaximumBorrowedItemsExceededException();
                } else if (fee) {
                    logger.info(String.format("Operation:Borrow Item;Loan Info:[%d,%s,%s,%s];State:Fail;Reason:Outstanding Fee Exists.", userId, titleISBN, copyNumber, date.toString()));
                    throw new OutstandingFeeExistsException();
                }
            } else {
                logger.info(String.format("Operation:Borrow Item;Loan Info:[%d,%s,%s,%s];State:Fail;Reason:The Item is Not Available.", userId, titleISBN, copyNumber, date.toString()));
                throw new ItemNotAvailableException();
            }
        }
    }

    /**
     * If user has more borrowed items then MAX_BORROWED_ITEMS, return false
     * indicating that user cannot borrow any more items
     *
     * @param userId
     * @return
     */
    public boolean checkLimit(int userId) {
        int borrowedItemsCount = (int) loanList
                .stream()
                .filter(loanEntity -> loanEntity.getUserId() == userId)
                .count();

        if (borrowedItemsCount >= Config.MAX_BORROWED_ITEMS) {
            return false;
        }

        return true;
    }

    public void renewItem(int userId, String isbn, String copyNumber, Date renewDate) throws NoSuchLoanExistsException, OutstandingFeeExistsException, ItemAlreadyRenewedException {

        if (FeeTable.getInstance().lookupFee(userId) > 0) {
            logger.info(String.format("Operation:Renew Item;Loan Info:[%d,%s,%s,%s];State:Fail;Reason:Outstanding Fee Exists.", userId, isbn, copyNumber, renewDate.toString()));
            throw new OutstandingFeeExistsException();
        }

        int index = -1;
        LoanEntity loanEntity = null;

        for (int i = 0 ; i < loanList.size() ; i++) {
            loanEntity = loanList.get(i);
            if (loanEntity.getUserId() == userId &&
                loanEntity.getISBN().equals(isbn) &&
                loanEntity.getCopyNumber().equals(copyNumber)) {
                index = i;
                break;
            }
        }


        if (index == -1) {
            logger.info(String.format("Operation:Renew Item;Loan Info:[%d,%s,%s,%s];State:Fail;Reason:The loan does not exist.", userId, isbn, copyNumber, renewDate.toString()));
            throw new NoSuchLoanExistsException();
        }

        if (loanEntity.getRenewCount() >= Config.MAX_RENEWALS) {
            logger.info(String.format("Operation:Renew Item;Loan Info:[%d,%s,%s,%s];State:Fail;Reason:Renewed Item More Than Once for the Same Loan.", userId, isbn, copyNumber, renewDate.toString()));
            throw new ItemAlreadyRenewedException();
        }
        loanList.get(index).setDate(new Date());
        loanList.get(index).incrementRenewCount();
        logger.info(String.format("Operation:Renew Item;Loan Info:[%d,%s,%s,%s];State:Success", userId, isbn, copyNumber, renewDate.toString()));
    }

    public void returnItem(int userId, String isbn, String copyNumber, Date returnDate) throws NoSuchLoanExistsException {
        Optional<LoanEntity> loanEntityOptional = loanList.stream().filter(loanEntity ->
                loanEntity.getUserId() == userId &&
                        loanEntity.getISBN().equals(isbn) &&
                        loanEntity.getCopyNumber().equals(copyNumber)
        ).findFirst();

        if (!loanEntityOptional.isPresent()) {
            logger.info(String.format("Operation:Return Item;Loan Info:[%d,%s,%s];State:Fail;Reason:The Loan Does Not Exist.", userId, isbn, copyNumber));
            throw new NoSuchLoanExistsException();
        }

        LoanEntity loanEntity = loanEntityOptional.get();
        loanList.remove(loanEntity);

        long time = returnDate.getTime() - loanEntity.getDate().getTime();

        logger.info(String.format("Operation:Return Item;Loan Info:[%d,%s,%s];State:Success", userId, isbn, copyNumber));

        if (time > Config.OVERDUE * Config.SIMULATED_DAY) {
            FeeTable.getInstance().applyFee(userId, time);
        }
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
     * If loan matches given predicate, return true
     *
     * @param loanEntityPredicate
     * @return
     */
    public boolean checkLoan(Predicate<LoanEntity> loanEntityPredicate) {
        return loanList.stream().anyMatch(loanEntityPredicate);
    }
}

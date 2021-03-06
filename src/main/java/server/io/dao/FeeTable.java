package main.java.server.io.dao;

import main.java.server.io.error.PendingLoansExistException;
import main.java.server.io.model.FeeEntity;
import main.java.server.io.model.LoanEntity;
import main.java.util.Config;
import main.java.util.Trace;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FeeTable {
    private Logger logger = Trace.getInstance().getLogger("operation_file");
    List<FeeEntity> feeList = new ArrayList<FeeEntity>();

    private static class FeeListHolder {
        private static final FeeTable INSTANCE = new FeeTable();
    }

    private FeeTable() {
        //set up the default list with some instances
        List<LoanEntity> loanList = LoanTable.getInstance().getLoanTable();

        long ts = new Date().getTime();
        loanList.forEach(loan -> applyFee(loan.getUserId(), ts - loan.getDate().getTime()));
        logger.info(String.format("Operation:Initialize FeeTable;FeeTable: %s", feeList));
    }

    ;

    public static FeeTable getInstance() {
        return FeeListHolder.INSTANCE;
    }

    public Optional<FeeEntity> lookup(Predicate<FeeEntity> loanEntityPredicate) {
        return feeList
                .stream()
                .filter(loanEntityPredicate)
                .findFirst();
    }

    private boolean checkuser(int id) {
        boolean result = true;
        int fee = 0;
        for (int i = 0; i < feeList.size(); i++) {
            int userid = (feeList.get(i)).getUserId();
            if (userid == id) {
                fee = fee + 1;
            } else {
                fee = fee + 0;
            }
        }
        if (fee == 0) {
            result = false;
        }
        return result;
    }

    public int lookupFee(int userId) {
        return feeList
                .stream()
                .filter(feeEntity -> feeEntity.getUserId() == userId)
                .mapToInt(feeEntity -> feeEntity.getFee())
                .sum();
    }

    public void applyFee(int id, long time) {
        int flag = 0;
        int index = 0;
        for (int i = 0; i < feeList.size(); i++) {
            int userid = (feeList.get(i)).getUserId();
            if (userid == id) {
                flag = flag + 1;
                index = i;
            }
        }
        int numberOfDaysOverdue = (int) ((time / (Config.SIMULATED_DAY)) - Config.OVERDUE);
        if (flag != 0) {
            if (numberOfDaysOverdue >= 0) {
                feeList.get(index).setFee(numberOfDaysOverdue + feeList.get(index).getFee());
                feeList.get(index).setUserId(id);
                logger.info(String.format("Operation:Apply OutStanding Fee;Fee Info:[%d,%d];State:Success", id, numberOfDaysOverdue + feeList.get(index).getFee()));
            } else {
                feeList.get(index).setFee(feeList.get(index).getFee());
                feeList.get(index).setUserId(id);
                logger.info(String.format("Operation:Apply OutStanding Fee;Fee Info:[%d,%d];State:Success", id, numberOfDaysOverdue + feeList.get(index).getFee()));
            }
        } else {
            if (numberOfDaysOverdue >= 0) {
                FeeEntity fee = new FeeEntity(id, numberOfDaysOverdue);
                feeList.add(fee);
                logger.info(String.format("Operation:Apply OutStanding Fee;Fee Info:[%d,%d];State:Success", id, numberOfDaysOverdue));
            } else {
                FeeEntity fee = new FeeEntity(id, 0);
                feeList.add(fee);
                logger.info(String.format("Operation:Apply OutStanding Fee;Fee Info:[%d,%d];State:Success", id, 0));
            }
        }


    }

    public List<FeeEntity> getFeeTable() {
        return feeList;
    }

    public void payFine(int userId) throws PendingLoansExistException {
        if (LoanTable.getInstance().checkLoanByUserId(userId)) {
            throw new PendingLoansExistException();
        }

        int fee = 0;
        int index = 0;

        boolean user = FeeTable.getInstance().checkuser(userId);

        if (user) {
            for (int m = 0; m < feeList.size(); m++) {
                if (feeList.get(m).getUserId() == userId) {
                    fee = feeList.get(m).getFee();
                    index = m;
                } else {
                    fee = 0;
                }
            }
            feeList.get(index).setFee(0);
            logger.info(String.format("Operation:Pay Fine;Fee Info:[%d,%d];State:Success", userId, fee));
        }
    }
}

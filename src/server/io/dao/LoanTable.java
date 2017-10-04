package server.io.dao;

import org.apache.log4j.Logger;
import server.io.model.LoanEntity;
import util.Trace;

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

    private LoanTable(){
    	//set up the default list with some instances
		LoanEntity loan = new LoanEntity(0,"9781442668584","1", new Date(),"0");
    	loanList.add(loan);
    	logger.info(String.format("Operation:Initialize LoanTable;LoanTable: %s", loanList));
    };

    public static final LoanTable getInstance() {
        return LoanListHolder.INSTANCE;
    }

	public Optional<LoanEntity> lookup(Predicate<LoanEntity> loanEntityPredicate) {
		return loanList
			.stream()
			.filter(loanEntityPredicate)
			.findFirst();
	}
	public Object createLoan(int i, String string, String string2, Date date) {
		return null;
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

	public boolean lookLimit(int j) {
		return false;
	}

	public boolean checkUser(int j) {
		return false;
	}

	public boolean checkLoan(String string, String string2) {
		return false;
	}

	public boolean checkLoan(String string) {
		return false;
	}
}

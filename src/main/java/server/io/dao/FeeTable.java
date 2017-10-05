package main.java.server.io.dao;

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

public class FeeTable {
	private Logger logger = Trace.getInstance().getLogger("operation_file");
	List<FeeEntity> feeList=new ArrayList<FeeEntity>();
    private static class FeeListHolder {
        private static final FeeTable INSTANCE = new FeeTable();
    }
    private FeeTable(){
    	//set up the default list with some instances
		FeeEntity fee = new FeeEntity(0,5);
    	feeList.add(fee);

		List<LoanEntity> loanList = LoanTable.getInstance().getLoanTable();

        long ts = new Date().getTime();
        loanList.forEach(loan -> applyFee(loan.getUserId(), ts - loan.getDate().getTime()));
		logger.info(String.format("Operation:Initialize FeeTable;FeeTable: %s", feeList));
    };

    public static final FeeTable getInstance() {
        return FeeListHolder.INSTANCE;
    }

	public Optional<FeeEntity> lookup(Predicate<FeeEntity> loanEntityPredicate) {
        return feeList
            .stream()
            .filter(loanEntityPredicate)
            .findFirst();
	}
	private boolean checkuser(int id) {
		boolean result=true;
		int fee = 0;
		for(int i=0;i<feeList.size();i++){
			int userid=(feeList.get(i)).getUserId();
			if(userid == id){
				fee=fee+1;
			}else{
				fee=fee+0;
			}
		}	
		if(fee==0){
			result=false;
		}
		return result;
	}
	public Object lookupfee(int j) {
		int fee=0;
		boolean user=FeeTable.getInstance().checkuser(j);
		if(user){
		for(int i=0;i<feeList.size();i++){
			int userid=(feeList.get(i)).getUserId();
			if(userid==j){
				fee=fee+feeList.get(i).getFee();
			}
		}
		}else{
			fee=0;
		}
		return fee;
	}
	public void applyFee(int id, long time) {
		int flag=0;
		int index=0;
		for(int i = 0;i<feeList.size();i++){
			int userid=(feeList.get(i)).getUserId();
			if(userid==id){
				flag=flag+1;
				index=i;
			}
		}
		int numberOfDaysOverdue = (int) ((time/(Config.STIMULATED_DAY))-Config.OVERDUE);
		if(flag!=0) {
			if(numberOfDaysOverdue >= 0){
				feeList.get(index).setFee(numberOfDaysOverdue + feeList.get(index).getFee());
				feeList.get(index).setUserId(id);
				logger.info(String.format("Operation:Apply OutStanding Fee;Fee Info:[%d,%d];State:Success", j,a+feeList.get(index).getFee()));
			}else{
				feeList.get(index).setFee(feeList.get(index).getFee());
				feeList.get(index).setUserId(id);
				logger.info(String.format("Operation:Apply OutStanding Fee;Fee Info:[%d,%d];State:Success", j,a+feeList.get(index).getFee()));
			}
		} else {
			if(numberOfDaysOverdue >= 0){
				FeeEntity fee=new FeeEntity(id, numberOfDaysOverdue);
				feeList.add(fee);
				logger.info(String.format("Operation:Apply OutStanding Fee;Fee Info:[%d,%d];State:Success", j,a));
			}else{
				FeeEntity fee=new FeeEntity(id,0);
				feeList.add(fee);
				logger.info(String.format("Operation:Apply OutStanding Fee;Fee Info:[%d,%d];State:Success", j,0));
			}
		}
		
		
	}
	public List<FeeEntity> getFeeTable() {
		return feeList;
	}

	public Object payFine(int id) {
		String result="";
		boolean oloan=LoanTable.getInstance().lookLimit(id);
		int fee=0;
		int index=0;
		boolean user=FeeTable.getInstance().checkuser(id);
		if(user){
			for(int m=0;m<feeList.size();m++){
				if(feeList.get(m).getUserId()==id){
					fee=feeList.get(m).getFee();
					index=m;
				}else{
					fee=0;
				}
			}
		}else{
			fee=0;
		}
		if(oloan==false){
			result="Borrowing Items Exist";
			logger.info(String.format("Operation:Pay Fine;Fee Info:[%d,%d];State:Fail;Reason:Borrowing Items Exist.", i,fee));
		}else{
			feeList.get(index).setFee(0);
			result="success";
			logger.info(String.format("Operation:Pay Fine;Fee Info:[%d,%d];State:Success", id,fee));
		}
		return result;
	}
	

}

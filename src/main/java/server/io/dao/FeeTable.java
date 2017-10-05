package main.java.server.io.dao;

import main.java.server.io.model.FeeEntity;
import main.java.server.io.model.LoanEntity;
import main.java.util.Config;
import main.java.util.Trace;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FeeTable {
	private Logger logger = Trace.getInstance().getLogger("operation_file");
	List<FeeEntity> feeList=new ArrayList<FeeEntity>();
    private static class FeeListHolder {
        private static final FeeTable INSTANCE = new FeeTable();
    }
    private FeeTable(){
    	//set up the default list with some instances
		FeeEntity fee=new Fee(0,5);
    	feeList.add(fee);
    	Initialization();
    };
    public static final FeeTable getInstance() {
        return FeeListHolder.INSTANCE;
    }
	public boolean lookup(int j) {
		boolean result=true;
		int fee = 0;
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
		if(fee!=0){
			result=false;
		}
		return result;
	}
	private boolean checkuser(int j) {
		boolean result=true;
		int fee = 0;
		for(int i=0;i<feeList.size();i++){
			int userid=(feeList.get(i)).getUserId();
			if(userid==j){
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
	public void applyfee(int j, long time) {
		int flag=0;
		int index=0;
		for(int i = 0;i<feeList.size();i++){
			int userid=(feeList.get(i)).getUserId();
			if(userid==j){
				flag=flag+1;
				index=i;
			}
		}
		int a=(int) ((time/(Config.STIMULATED_DAY))-Config.OVERDUE);
		if(flag!=0){
			if(a>=0){
				feeList.get(index).setFee(a+feeList.get(index).getFee());
				feeList.get(index).setUserId(j);
				logger.info(String.format("Operation:Apply OutStanding Fee;Fee Info:[%d,%d];State:Success", j,a+feeList.get(index).getFee()));
			}else{
				feeList.get(index).setFee(feeList.get(index).getFee());
				feeList.get(index).setUserId(j);
				logger.info(String.format("Operation:Apply OutStanding Fee;Fee Info:[%d,%d];State:Success", j,a+feeList.get(index).getFee()));
			}
		}else{
			if(a>=0){
				FeeEntity fee=new FeeEntity(j,a);
				feeList.add(fee);
				logger.info(String.format("Operation:Apply OutStanding Fee;Fee Info:[%d,%d];State:Success", j,a));
			}else{
				FeeEntity fee=new FeeEntity(j,0);
				feeList.add(fee);
				logger.info(String.format("Operation:Apply OutStanding Fee;Fee Info:[%d,%d];State:Success", j,0));
			}
		}
		
		
	}
	public List<FeeEntity> getFeeTable() {
		return feeList;
	}
	public void Initialization(){
    	List<LoanEntity> loanList=LoanTable.getInstance().getLoanTable();
    	for(int i=0;i<loanList.size();i++){
    		applyfee(loanList.get(i).getUserId(), new Date().getTime()-loanList.get(i).getDate().getTime());
    	}
    	logger.info(String.format("Operation:Initialize FeeTable;FeeTable: %s", feeList));
	}
	public Object payfine(int i) {
		String result="";
		boolean oloan=LoanTable.getInstance().lookLimit(i);
		int fee=0;
		int index=0;
		boolean user=FeeTable.getInstance().checkuser(i);
		if(user){
			for(int m=0;m<feeList.size();m++){
				if(feeList.get(m).getUserId()==i){
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
			feeList.get(index).setUserId(i);
			feeList.get(index).setFee(0);
			result="success";
			logger.info(String.format("Operation:Pay Fine;Fee Info:[%d,%d];State:Success", i,fee));
		}
		return result;
	}
	

}

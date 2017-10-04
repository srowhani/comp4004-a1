package server.io.tables;

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import org.apache.log4j.Logger;
import server.io.model.UserEntity;
import util.Trace;

import java.util.ArrayList;
import java.util.List;

public class UserTable {
	private Logger logger = Trace.getInstance().getLogger("operation_file");
	List<UserEntity> userList = new ArrayList<UserEntity>();

    private static class UserListHolder {
        private static final UserTable INSTANCE = new UserTable();
    }

    private UserTable() {

    	//set up the default list with some instances
    	String[] passwordList = new String[]{"Zhibo","Yu","Michelle","Kevin","Sun"};
    	String[] usernameList = new String[]{"Zhibo@carleton.ca","Yu@carleton.ca","Michelle@carleton.ca","Kevin@carleton.ca","Sun@carleton.ca"};

    	for(int i=0; i < usernameList.length; i++){
			userList.add(new UserEntity(i, usernameList[i], passwordList[i]));
		}
    	logger.info(String.format("Operation:Initialize UserTable;UserTable: %s", userList));
    };

    public static final UserTable getInstance() {
        return UserListHolder.INSTANCE;
    }

    public boolean createUser(String string, String string2) {
    	boolean result;
		int flag = 0;

		for(int i = 0; i < userList.size(); i++){
			String email = userList.get(i).getUsername();
			if(email.equalsIgnoreCase(string)) {
				flag += 1;
			}
		}

		if(flag == 0){
			logger.info(String.format("Operation:Create New User;User Info:[%s,%s];State:Success", string,string2));
			UserEntity userEntity = new UserEntity(userList.size(),string,string2);

			return userList.add(userEntity);
		}
		logger.info(String.format("Operation:Create New User;User Info:[%s,%s];State:Fail;Reason:The User already existed.", string,string2));
		return false;
	}
	public boolean lookup(int j) {
		boolean result=true;
		int flag=0;
		for(int i=0;i<userList.size();i++){
			int userid=(userList.get(i)).getId();
			if(userid==j){
				flag=flag+1;
			}else{
				flag=flag+0;	
			}
		}
		if(flag==0){
			result=false;
		}
		return result;
	}
	public List<UserEntity> getUserTable() {
		return userList;
	}
//	public Object delete(int i) {
//		//Since the userid in "User Creation" is automatically assigned to the user,upon its creation.
//		//Each user has a unique userid.Even it is deleted,its userid can not be assigned to other user.
//		//To maintain the correctness of the data,here instead delete index from the List.
//		//I choose to remove the user's information instead the whole index.Keep its userid as reference.
//		String result="";
//		boolean loan=LoanTable.getInstance().checkUser(i);
//		int flag=0;
//		int index=0;
//		for(int j=0;j<userList.size();j++){
//			if(userList.get(j).getId()==i){
//				index=j;
//				flag=flag+1;
//			}else{
//				flag=flag+0;
//			}
//		}
//
//		if(flag==0){
//			result="The User Does Not Exist";
//			logger.info(String.format("Operation:Delete User;User Info:[%s,%s];State:Fail;Reason:The User Does Not Exist.", "N/A","N/A"));
//		}else{
//			boolean fee=FeeTable.getInstance().lookup(i);
//			String string=userList.get(index).getUsername();
//			String string2=userList.get(index).getPassword();
//			if(fee && loan){
//				userList.get(index).setId(i);
//				userList.get(index).setPassword("N/A");
//				userList.get(index).setUsername("N/A");
//				result="success";
//				logger.info(String.format("Operation:Delete User;User Info:[%s,%s];State:Success", string,string2));
//			}else if(fee==false){
//				result="Outstanding Fee Exists";
//				logger.info(String.format("Operation:Delete User;User Info:[%s,%s];State:Fail;Reason:Outstanding Fee Exists.", string,string2));
//			}else if(loan==false){
//				result="Active Loan Exists";
//				logger.info(String.format("Operation:Delete User;User Info:[%s,%s];State:Fail;Reason:Active Loan Exists.", string,string2));
//			}
//		}
//
//		return result;
//
//	}
	public int lookup(String string) {
		int userid=-1;
		for(int i=0;i<userList.size();i++){
			if(userList.get(i).getUsername().equalsIgnoreCase(string)){
				userid=i;
			}
		}
		return userid;
	}
	public int checkUser(String string, String string2) {
		int result=0;
		int flag=0;
		int index=0;
		for(int i=0;i<userList.size();i++){
			if(userList.get(i).getUsername().equalsIgnoreCase(string)){
				flag=flag+1;
				index=i;
			}else{
				flag=flag+0;
			}
		}
		boolean password=userList.get(index).getPassword().equalsIgnoreCase(string2);
		if(flag!=0 && password){
			result=0;
		}else if(flag==0){
			result=2;
		}else if(password==false){
			result=1;
		}
		return result;
	}}

package server.io.tables;

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import org.apache.log4j.Logger;
import server.io.model.UserEntity;
import util.Trace;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserTable {
	private final Logger logger = Trace.getInstance().getLogger("operation_file");
	private List<UserEntity> userList = new ArrayList<>();

	public static final int
			USER_AUTHENTICATED = 0,
			INVALID_CREDENTIALS = 1,
			NO_USER_FOUND = 2;


    private static class UserListHolder {
        private static final UserTable INSTANCE = new UserTable();
    }


    private UserTable() {
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

    public boolean createUser(String username, String password) {
    	boolean result;
		int flag = 0;

		for(int i = 0; i < userList.size(); i++){
			String email = userList.get(i).getUsername();
			if(email.equalsIgnoreCase(username)) {
				flag += 1;
			}
		}

		if(flag == 0){
			logger.info(String.format("Operation:Create New User;User Info:[%s,%s];State:Success", username, password));
			UserEntity userEntity = new UserEntity(userList.size(), username, password);

			return userList.add(userEntity);
		}
		logger.info(String.format("Operation:Create New User;User Info:[%s,%s];State:Fail;Reason:The User already existed.", username, password));
		return false;
	}

	public Optional<UserEntity> lookup(int id) {
		return userList
				.stream()
				.filter(user -> user.getId() == id)
				.findFirst();
	}

	public Optional<UserEntity> lookup(String username) {
		return userList
				.stream()
				.filter(user -> user.getUsername().equals(username))
				.findFirst();
	}

	public List<UserEntity> getUserTable() {
		return userList;
	}

	public int checkUser(String username, String password) {
		Optional<UserEntity> optionalUser = userList
				.stream()
				.filter(user -> user.getUsername().equals(username))
				.findFirst();

		if (!optionalUser.isPresent()) {
			return NO_USER_FOUND;
		}

		if (optionalUser.get().getPassword().equals(password)) {
			return USER_AUTHENTICATED;
		}

		return INVALID_CREDENTIALS;
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
}

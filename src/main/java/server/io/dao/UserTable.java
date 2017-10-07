package main.java.server.io.dao;

import main.java.server.io.error.InvalidUserCredentialsException;
import main.java.server.io.error.LoanExistsException;
import main.java.server.io.error.OutstandingFeeExistsException;
import main.java.server.io.error.UserEntityNotFoundException;
import main.java.server.io.model.FeeEntity;
import main.java.server.io.model.LoanEntity;
import main.java.server.io.model.UserEntity;
import main.java.util.Trace;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class UserTable {
    private final Logger logger = Trace.getInstance().getLogger("operation_file");
    private List<UserEntity> userList = new ArrayList<>();
    private int numUsers = 0;

    private static class UserListHolder {
        private static final UserTable INSTANCE = new UserTable();
    }

    private UserTable() {
        String[] passwordList = new String[]{"Zhix bo", "Yu", "Michelle", "Kevin", "Sun"};
        String[] usernameList = new String[]{"Zhibo@carleton.ca", "Yu@carleton.ca", "Michelle@carleton.ca", "Kevin@carleton.ca", "Sun@carleton.ca"};

        for (int i = 0; i < usernameList.length; i++) {
            add(usernameList[i], passwordList[i]);
        }
        logger.info(String.format("Operation:Initialize UserTable;UserTable: %s", userList));
    }

    ;

    public static final UserTable getInstance() {
        return UserListHolder.INSTANCE;
    }

    public UserEntity add(String username, String password) {
        if (userList.stream().anyMatch(user -> user.getUsername().equals(username))) {
            logger.info(String.format("Operation:Create New User;User Info:[%s,%s];State:Fail;Reason:The User already existed.", username, password));
            return null;
        }
        UserEntity userEntity = new UserEntity(numUsers++, username, password);

        userList.add(userEntity);
        logger.info(String.format("Operation:Create New User;User Info:[%s,%s];State:Success", username, password));

        return userEntity;
    }

    public Optional<UserEntity> lookup(Predicate<UserEntity> predicate) {
        return userList
                .stream()
                .filter(predicate)
                .findFirst();
    }

    public List<UserEntity> getUserTable() {
        return userList;
    }

    public void validate(String username, String password) throws InvalidUserCredentialsException, UserEntityNotFoundException {
        Optional<UserEntity> optionalUser = userList
                .stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst();

        if (!optionalUser.isPresent()) {
            throw new UserEntityNotFoundException();
        }

        if (!optionalUser.get().getPassword().equals(password)) {
            throw new InvalidUserCredentialsException();
        }
    }

    public UserEntity remove(int id) throws UserEntityNotFoundException, OutstandingFeeExistsException, LoanExistsException {
        Optional<UserEntity> user = userList
                .stream()
                .filter(userEntity -> userEntity.getId() == id)
                .findFirst();

        if (!user.isPresent()) {
            throw new UserEntityNotFoundException();
        }

        Optional<FeeEntity> feeEntityOptional = FeeTable.getInstance().lookup(
                feeEntity -> feeEntity.getUserId() == id);

        if (feeEntityOptional.isPresent()) {
            throw new OutstandingFeeExistsException();
        }

        Optional<LoanEntity> loanEntityOptional = LoanTable.getInstance().lookup(
                loanEntity -> loanEntity.getUserId() == id);

        if (loanEntityOptional.isPresent()) {
            throw new LoanExistsException();
        }

        userList.remove(user.get());
        return user.get();
    }
}

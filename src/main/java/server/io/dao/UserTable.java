package main.java.server.io.dao;

import main.java.server.io.error.*;
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

    private static class UserListHolder {
        private static final UserTable INSTANCE = new UserTable();
    }

    private UserTable() {
        logger.info(String.format("Operation:Initialize UserTable;UserTable: %s", userList));
    }

    public static UserTable getInstance() {
        return UserListHolder.INSTANCE;
    }

    public UserEntity add(String username, String password) throws UserEntityExistsException {
        if (userList.stream().anyMatch(user -> user.getUsername().equals(username))) {
            logger.info(String.format("Operation:Create New User;User Info:[%s,%s];State:Fail;Reason:The User already existed.", username, password));
            throw new UserEntityExistsException();
        }
        UserEntity userEntity = new UserEntity(userList.size(), username, password);

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

    public UserEntity remove(int userId) throws UserEntityNotFoundException, OutstandingFeeExistsException, LoanExistsException {
        Optional<UserEntity> user = userList
                .stream()
                .filter(userEntity -> userEntity.getId() == userId)
                .findFirst();

        if (!user.isPresent()) {
            throw new UserEntityNotFoundException();
        }

        if (FeeTable.getInstance().lookupFee(userId) > 0) {
            throw new OutstandingFeeExistsException();
        }

        if (LoanTable.getInstance().checkLoanByUserId(userId)) {
            throw new LoanExistsException();
        }

        userList.remove(user.get());
        return user.get();
    }
}

package main.java.server.io.dao;

import main.java.server.io.error.UserEntityExistsException;
import main.java.server.io.model.UserEntity;
import org.junit.Test;

import java.util.Optional;
import java.util.stream.Collectors;

import static org.apache.log4j.helpers.LogLog.warn;
import static org.junit.Assert.*;
import static util.Assert.assertDoesNotThrow;

public class UserTableTests {
    @Test
    public void getInstanceNonNull() {
        assertDoesNotThrow(() -> UserTable.getInstance());
    }

    @Test
    public void createUser() {
        UserTable userTable = UserTable.getInstance();
        String username = "username";
        String password = "password";
        try {
            userTable.add(username, password);
        } catch (UserEntityExistsException e) {
            warn(e.getMessage());
        }

        assertTrue(userTable.getUserTable().stream().anyMatch(item ->
                item.getUsername().equals(username) && item.getPassword().equals(password)
        ));

    }

    @Test
    public void deleteUser() {
        UserTable userTable = UserTable.getInstance();

        userTable.getUserTable()
                .stream()
                .map(u -> u.getId())
                .collect(Collectors.toList())
                .forEach(id -> {
                    try {
                        FeeTable.getInstance().payFine(id);
                        // TODO: Fix loantable pay loan
                        userTable.remove(id);
                    } catch (Exception e) {
                        warn(e.getMessage());
                    }
                });

        assertEquals(userTable.getUserTable().size(), 0);
    }

    @Test
    public void lookupRetrievesOptional() {
        createUser();

        UserTable userTable = UserTable.getInstance();
        UserEntity userEntity = userTable.getUserTable().get(0);

        Optional<UserEntity> userEntityOptional = userTable.lookup(user -> user.getId() == userEntity.getId());
        assertTrue(userEntityOptional.isPresent());
        assertEquals(userEntityOptional.get(), userEntity);

        userEntityOptional = userTable.lookup(user -> user.getUsername().equals(userEntity.getUsername()));
        assertTrue(userEntityOptional.isPresent());
        assertEquals(userEntityOptional.get(), userEntity);
    }
}

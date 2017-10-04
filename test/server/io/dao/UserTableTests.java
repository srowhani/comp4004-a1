package server.io.dao;

import org.junit.Test;
import server.io.model.UserEntity;

import java.util.Optional;
import java.util.stream.Collectors;

import static lib.Assert.assertDoesNotThrow;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class UserTableTests {
    @Test
    public void getInstanceNonNull () {
        assertDoesNotThrow(() -> UserTable.getInstance());
    }

    @Test
    public void createUser () {
        UserTable userTable = UserTable.getInstance();
        String username = "username";
        String password = "password";
        userTable.add(username, password);

        assertTrue(userTable.getUserTable().stream().anyMatch(item ->
            item.getUsername().equals(username) && item.getPassword().equals(password)
        ));

    }

    @Test
    public void deleteUser () {
        UserTable userTable = UserTable.getInstance();

        userTable.getUserTable()
                .stream()
                .map(u -> u.getId())
                .collect(Collectors.toList())
                .forEach(id -> {
                    userTable.remove(id);
                });

        assertEquals(userTable.getUserTable().size(), 0);
    }

    @Test
    public void lookupRetrievesOptional () {
        createUser();

        UserTable userTable = UserTable.getInstance();
        UserEntity userEntity = userTable.getUserTable().get(0);

        Optional<UserEntity> userEntityOptional = userTable.lookup(user -> user.getId() == userEntity.getId());
        assertTrue(userEntityOptional.isPresent());
        assertEquals(userEntityOptional.get(), userEntity);

        userEntityOptional = userTable.lookup(user -> user.getUsername() == userEntity.getUsername());
        assertTrue(userEntityOptional.isPresent());
        assertEquals(userEntityOptional.get(), userEntity);
    }
}

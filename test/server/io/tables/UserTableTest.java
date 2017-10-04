package server.io.tables;

import org.junit.Test;
import server.io.model.UserEntity;

import static lib.Assert.assertDoesNotThrow;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class UserTableTest {
    @Test
    public void getInstanceNonNull () {
        assertDoesNotThrow(() -> UserTable.getInstance());
    }

    @Test
    public void createUser () {
        UserTable userTable = UserTable.getInstance();
        String username = "username";
        String password = "password";
        userTable.createUser(username, password);
        assertTrue(userTable.getUserTable().stream().anyMatch(item ->
            item.getUsername().equals(username) && item.getPassword().equals(password)
        ));
    }

    @Test
    public void deleteUser () {
        fail("Not yet implemented :(");
    }

    @Test
    public void lookupRetrievesOptional () {
        createUser();

        UserTable userTable = UserTable.getInstance();
        UserEntity userEntity = userTable.getUserTable().get(0);
        assertEquals(userTable.lookup(userEntity.getId()).get(), userEntity);
        assertEquals(userTable.lookup(userEntity.getUsername()).get(), userEntity);

    }

}

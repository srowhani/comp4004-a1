package server.io.tables;

import org.junit.Test;

import static lib.Assert.assertDoesNotThrow;
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
            item.getUsername() == username && item.getPassword() == password
        ));
    }

    @Test
    public void deleteUser () {
        fail("Not yet implemented :(");
    }

}

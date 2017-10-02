package server.io.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UserModelTests {

    @Test
    public void canInstantiate () {
        assertNotNull(new User(1, "username", "password"));
    }

    @Test
    public void canAccessProperties () {
        int userId = 1;
        String username = "testuser";
        String password = "password";
        User testUser = new User(userId, username, password);

        assertEquals(testUser.getId(), userId);
        assertEquals(testUser.getUsername(), username);
        assertEquals(testUser.getPassword(), password);

    }
}

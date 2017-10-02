package server.io.model;

import org.junit.Test;

import static lib.Assert.assertDoesNotThrow;
import static org.junit.Assert.assertEquals;

public class UserEntityTests implements ModelTest {

    @Test
    public void canInstantiate () {
        assertDoesNotThrow(() -> new UserEntity(1, "username", "password"));
    }

    @Test
    public void canAccessProperties () {
        int userId = 1;
        String username = "testuser";
        String password = "password";
        UserEntity testUserEntity = new UserEntity(userId, username, password);

        assertEquals(testUserEntity.getId(), userId);
        assertEquals(testUserEntity.getUsername(), username);
        assertEquals(testUserEntity.getPassword(), password);

    }
}

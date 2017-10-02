package server.io.model;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class UserModelTests {
    
    @Test
    public void canInstantiate () {
        assertNotNull(new User(1, "username", "password"));
    }
}

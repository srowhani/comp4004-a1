package main.java.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConfigurationTest {
    @Test
    public void assertDefaultProperties() {
        assertEquals(Config.MAX_CLIENTS, 5);
        assertEquals(Config.DEFAULT_PORT, 5050);
        assertEquals(Config.DEFAULT_HOST, "127.0.0.1");
        assertEquals(Config.MAX_BORROWED_ITEMS, 3);
        assertEquals(Config.SIMULATED_DAY, 86400000);
        assertEquals(Config.OVERDUE, 5);
        assertEquals(Config.CLERK_PASSWORD, "admin");
    }
}

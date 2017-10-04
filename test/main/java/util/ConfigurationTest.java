package main.java.util;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConfigurationTest {
    private Config config;

    @Before
    public void setup () {
        config = new Config();
    }

    @Test
    public void assertDefaultProperties () {
        assertEquals(config.MAX_CLIENTS, 5);
        assertEquals(config.DEFAULT_PORT, 5050);
        assertEquals(config.DEFAULT_HOST, "127.0.0.1");
        assertEquals(config.MAX_BORROWED_ITEMS, 3);
        assertEquals(config.STIMULATED_DAY, 1 * 60 * 1000);
        assertEquals(config.OVERDUE, 5);
        assertEquals(config.CLERK_PASSWORD, "admin");
    }
}

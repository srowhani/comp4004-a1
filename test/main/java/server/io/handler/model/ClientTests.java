package main.java.server.io.handler.model;

import org.junit.Test;

import static util.Assert.assertDoesNotThrow;
import static org.junit.Assert.assertEquals;

public class ClientTests {
    @Test
    public void ableToInstantiate () {
        assertDoesNotThrow(() -> new Client());
    }

    @Test
    public void setClientState () {
        Client c = new Client();
        c.setState(ClientState.USER);
        assertEquals(c.getState(), ClientState.USER);
        c.setState(null);
        assertEquals(c.getState(), null);

    }
}

package main.java.server.io.handler.model;

import main.java.server.io.handler.model.Client;
import main.java.server.io.handler.model.ClientState;
import org.junit.Test;

import static lib.Assert.assertDoesNotThrow;
import static org.junit.Assert.assertEquals;

public class ClientTests {
    @Test
    public void ableToInstantiate () {
        assertDoesNotThrow(() -> new Client());
    }

    @Test
    public void setClientState () {
        Client c = new Client();
        c.setState(ClientState.ONLINE);
        assertEquals(c.getState(), ClientState.ONLINE);
        c.setState(null);
        assertEquals(c.getState(), null);

    }
}

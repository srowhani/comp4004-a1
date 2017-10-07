package main.java.server.io.handler;

import main.java.server.io.handler.model.ClientState;
import main.java.server.io.handler.model.ServerOutput;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static util.Assert.assertDoesNotThrow;

public class ClientInputReaderTests {
    @Test
    public void canInstantiate() {
        assertDoesNotThrow(() -> new ClientInputReader());
    }

    @Test
    public void processInputReturnsValidOutput() {
        ClientInputReader clientInputReader = new ClientInputReader().pipe(new ClientInputHandler());

        ServerOutput serverOutput = clientInputReader.processInput("test string", ClientState.CLERK);

        assertNotNull(serverOutput);
    }
}

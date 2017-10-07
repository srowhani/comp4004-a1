package main.java.server.io.handler.model;

import org.junit.Test;

import static lib.Assert.assertDoesNotThrow;
import static org.junit.Assert.assertEquals;

public class ServerOutputTests {
    @Test
    public void canInstantiate () {
        assertDoesNotThrow(() -> new ServerOutput());
    }

    @Test
    public void canAccessProperties () {
        String testOutput = "hello world!";
        ServerOutput serverOutput = new ServerOutput();
        serverOutput.setOutput(testOutput);
        assertEquals(serverOutput.getOutput(), testOutput);

        ClientState outputState = ClientState.CLERK;
        serverOutput.setState(outputState);
        assertEquals(serverOutput.getState(), outputState);
    }
}

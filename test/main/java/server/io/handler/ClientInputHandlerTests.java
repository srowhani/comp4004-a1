package main.java.server.io.handler;

import org.junit.Test;

import static util.Assert.assertDoesNotThrow;

public class ClientInputHandlerTests {
    ClientInputHandler clientInputHandler;

    @Test
    public void canInstantiate() {
        assertDoesNotThrow(() -> clientInputHandler = new ClientInputHandler());
    }

    @Test
    public void loginAsClerk() {
//        clientInputHandler.clerkLogin();
    }

    @Test
    public void borrowBook() {
//        UserTable.getInstance().add("")
//        clientInputHandler.userLogin();
//        clientInputHandler.borrowBook("ISBN");
    }
}

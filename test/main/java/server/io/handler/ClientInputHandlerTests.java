package main.java.server.io.handler;

import main.java.server.io.dao.TitleTable;
import main.java.server.io.dao.UserTable;
import main.java.server.io.handler.model.ClientState;
import main.java.server.io.handler.model.ServerOutput;
import main.java.server.io.model.TitleEntity;
import main.java.server.io.model.UserEntity;
import main.java.util.Config;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Date;
import java.util.UUID;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static util.Assert.assertDoesNotThrow;

public class ClientInputHandlerTests {
    private UserEntity userActor;

    ClientInputHandler clientInputHandler;

    @Before
    public void setup () {
        if (clientInputHandler == null) {
            assertDoesNotThrow(() -> clientInputHandler = new ClientInputHandler());
        }
    }

    @Test
    public void loginAsClerk() {
        ServerOutput serverOutput = clientInputHandler.clerkLogin(Config.CLERK_PASSWORD);
        assertNotNull(serverOutput);
    }

    @Test
    public void loginAsUser() {
        System.out.println(userActor);
        ServerOutput serverOutput = clientInputHandler.userLogin(String.format("%s,%s", userActor.getUsername(), userActor.getPassword()));
        assertNotNull(serverOutput);
    }
    @Test
    public void borrowBook() {
        loginAsUser();
        TitleEntity titleEntity = TitleTable.getInstance().getTitleTable().get(0);

        ServerOutput serverOutput = clientInputHandler.borrowBook(titleEntity.getISBN());

        assertNotNull(serverOutput);
    }

    @Test
    public void renewBookTest () {
        fail("Not yet impl");
    }

    @Test
    public void returnBookTest () {
        fail("not yet implemented");
    }

    @Test
    public void payFineTest () {
        fail("Not yet implemented");
    }

    @Test
    public void createItemTest () {
//        clientInputHandler.createItem();

        fail("Not yet implemented");
    }

    @Test
    public void deleteItemTest () {
//        clientInputHandler.deleteItem();
        fail("not yet impl");
    }

    @Test
    public void createTitleTest () {
//        clientInputHandler.createTitle();

        fail("Not yet implemented");
    }

    @Test
    public void deleteTitleTest () {
//        clientInputHandler.deleteTitle();
        fail("not yet impl");
    }

    @Test
    public void createUserTest () {
        ServerOutput serverOutput = clientInputHandler.createUser(String.format("'%s,%s'", "test-user" + seed(), "test-password" + seed()));

        assertEquals(serverOutput.getOutput(), "Success!");
        assertEquals(serverOutput.getState(), ClientState.CLERK);
    }

    @Test
    public void deleteUserTest () {
        ServerOutput serverOutput = clientInputHandler.deleteUser(String.format("'%s,%s'", "test-user" + seed(), "test-password" + seed()));
        fail("not yet impl");
    }

    public String seed () {
        return UUID.randomUUID().toString();
    }
}

package main.java.server.io.handler;

import main.java.server.io.dao.ItemTable;
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
    ClientInputHandler clientInputHandler;

    @Before
    public void setup () {
        UserTable.getInstance().getUserTable().clear();
        TitleTable.getInstance().getTitleTable().clear();
        ItemTable.getInstance().getItemTable().clear();
        if (clientInputHandler == null) {
            assertDoesNotThrow(() -> clientInputHandler = new ClientInputHandler());
        }
    }

    @Test
    public void loginAsClerk() {
        ServerOutput serverOutput = clientInputHandler.clerkLogin(Config.CLERK_PASSWORD);
        assertEquals(serverOutput.getState(), ClientState.CLERK);

        serverOutput = clientInputHandler.clerkLogin("wrong password");
        assertEquals(serverOutput.getState(), ClientState.CLERKLOGIN);
    }

    @Test
    public void loginAsUser() {
        String csv = String.format("%s-%s,%s", "test-user", seed(), "password");
        clientInputHandler.createUser(csv);
        ServerOutput serverOutput = clientInputHandler.userLogin(csv);
        assertEquals(serverOutput.getState(), ClientState.USER);
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
        String isbn = "1928374655463";
        if (!TitleTable.getInstance().lookup(titleEntity -> titleEntity.getISBN().equals(isbn)).isPresent()) {
            createTitleTest();
        }

        ServerOutput serverOutput = clientInputHandler.createItem(isbn);
        assertEquals(serverOutput.getOutput(), "Success!");
    }

    @Test
    public void deleteItemTest () {
        String isbn = "1928374655463";

        createItemTest();
        createItemTest();

        ServerOutput serverOutput = clientInputHandler.deleteItem(String.format("%s,%s", isbn, "0"));
        assertEquals(serverOutput.getOutput(), "Success!");
        serverOutput = clientInputHandler.deleteItem(String.format("%s,%s", isbn, "1"));
        assertEquals(serverOutput.getOutput(), "Success!");

    }

    @Test
    public void createTitleTest () {
        String isbn = "1928374655463";
        String bookTitle = "Timothy goes to school";
        ServerOutput serverOutput = clientInputHandler.createTitle(String.format("%s,%s", isbn, bookTitle));
        assertEquals(serverOutput.getOutput(), "Success!");
        assertEquals(serverOutput.getState(), ClientState.CLERK);
    }

    @Test
    public void deleteTitleTest () {
        String isbn = "1928374655463";
        createTitleTest();
        ServerOutput serverOutput = clientInputHandler.deleteTitle(isbn);
        assertEquals(serverOutput.getOutput(), "Success!");
        assertEquals(serverOutput.getState(), ClientState.CLERK);
    }

    @Test
    public void createUserTest () {
        ServerOutput serverOutput = clientInputHandler.createUser(String.format("'%s,%s'", "test-user" + seed(), "test-password" + seed()));

        assertEquals(serverOutput.getOutput(), "Success!");
        assertEquals(serverOutput.getState(), ClientState.CLERK);
    }

    @Test
    public void deleteUserTest () {
        String seed = seed();
        ServerOutput serverOutput = clientInputHandler.createUser(String.format("%s,%s", "test-user" + seed, "test-password" + seed));
        assertEquals(serverOutput.getOutput(), "Success!");
        assertEquals(serverOutput.getState(), ClientState.CLERK);
        serverOutput = clientInputHandler.deleteUser("test-user" + seed);
        assertEquals(serverOutput.getOutput(), "Success!");
        assertEquals(serverOutput.getState(), ClientState.CLERK);
    }

    public String seed () {
        return UUID.randomUUID().toString();
    }
}

package main.java.server.io.handler;

import main.java.server.io.dao.*;
import main.java.server.io.error.NoSuchISBNExistsException;
import main.java.server.io.error.TitleEntityExistsException;
import main.java.server.io.handler.model.ClientState;
import main.java.server.io.handler.model.ServerOutput;
import main.java.server.io.model.ItemEntity;
import main.java.server.io.model.TitleEntity;
import main.java.server.io.model.UserEntity;
import main.java.util.Config;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static main.java.server.io.handler.model.ClientState.PAYFINE;
import static org.apache.log4j.helpers.LogLog.warn;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static util.Assert.assertDoesNotThrow;

public class ClientInputHandlerTests {
    ClientInputHandler clientInputHandler;

    @Before
    public void setup () {
        UserTable.getInstance().getUserTable().clear();
        TitleTable.getInstance().getTitleTable().clear();
        ItemTable.getInstance().getItemTable().clear();
        FeeTable.getInstance().getFeeTable().clear();
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
        TitleEntity titleEntity;
        try {
            titleEntity = TitleTable.getInstance().createTitle("1234567890139", "Foobar: The return of the baz");
            try {
                ItemTable.getInstance().addItem(titleEntity.getISBN());
                ServerOutput serverOutput = clientInputHandler.borrowBook(titleEntity.getISBN());
                assertNotNull(serverOutput);
            } catch (NoSuchISBNExistsException e) {
                fail(e.getMessage());
            }

        } catch (TitleEntityExistsException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void renewBookTest () {
        String username = "test-user-" + seed();
        String password = "password";
        String userCsv = String.format("%s,%s", username, password);

        ServerOutput serverOutput = clientInputHandler.createUser(userCsv);

        assertEquals("Success!", serverOutput.getOutput());

        Optional<UserEntity> userEntityOptional = UserTable.getInstance().lookup(user -> user.getUsername().equals(username));

        assertTrue(userEntityOptional.isPresent());

        borrowBook();
        Optional<ItemEntity> itemEntityOptional = ItemTable.getInstance().lookup(title -> title.getISBN().equals("1234567890139"));

        assertTrue(itemEntityOptional.isPresent());

        ItemEntity itemEntity = itemEntityOptional.get();

        LoanTable.getInstance().getLoanTable().forEach(loanEntity -> {
            if (loanEntity.getUserId() == userEntityOptional.get().getId()) {
                loanEntity.setDate(new Date(System.currentTimeMillis() - (7 * Config.SIMULATED_DAY)));
            }
        });

        clientInputHandler.renewBook(String.format("%s,%s,%s", username, itemEntity.getISBN(), itemEntity.getCopyNumber()));
    }

    @Test
    public void returnBookTest () {
        fail("not yet implemented");
    }

    @Test
    public void payFineTest () {
        String username = "test-user-" + seed();
        String password = "password";
        String userCsv = String.format("%s,%s", username, password);

        ServerOutput serverOutput = clientInputHandler.createUser(userCsv);
        assertEquals("Success!", serverOutput.getOutput());

        Optional<UserEntity> userEntityOptional = UserTable.getInstance().lookup(user -> user.getUsername().equals(username));

        assertTrue(userEntityOptional.isPresent());

        UserEntity userEntity = userEntityOptional.get();

        long timeOverdue = (Config.OVERDUE + 3) * Config.SIMULATED_DAY;
        FeeTable.getInstance().applyFee(userEntity.getId(), timeOverdue);

        assertEquals(FeeTable.getInstance().lookupFee(userEntity.getId()), 3);
        clientInputHandler.payFine(username);
        assertEquals(FeeTable.getInstance().lookupFee(userEntity.getId()), 0);


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

package features;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import main.java.server.io.dao.ItemTable;
import main.java.server.io.dao.LoanTable;
import main.java.server.io.dao.TitleTable;
import main.java.server.io.dao.UserTable;
import main.java.server.io.handler.ClientInputHandler;
import main.java.server.io.handler.model.ServerOutput;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class StepDefinitionsImpl {
    ClientInputHandler outputHandler = new ClientInputHandler();
    ServerOutput serverOutput;
    @Given("^User \"([^\"]*)\" created")
    public void userCreated(String arg0) throws Throwable {
        serverOutput = outputHandler.createUser(String.format("%s,%s", arg0, "password"));
    }

    @When("^Attempting to add new User \"([^\"]*)\"$")
    public void attemptingToAddNewUser(String arg0) throws Throwable {
        serverOutput = outputHandler.createUser(String.format("%s,%s", arg0, "password"));
        assertNotNull(serverOutput);
    }

    @Given("^Title \"([^\"]*)\" with ISBN \"([^\"]*)\" exists$")
    public void titleAlreadyExists(String title, String isbn) throws Throwable {
        serverOutput = outputHandler.createTitle(String.format("%s,%s", isbn, title));
        assertEquals(serverOutput.getOutput(), "Success!");
    }

    @Given("^Each test is independent of each other$")
    public void eachTestIsIndependentOfEachOther() throws Throwable {
        UserTable.getInstance().getUserTable().clear();
        TitleTable.getInstance().getTitleTable().clear();
        LoanTable.getInstance().getLoanTable().clear();
        ItemTable.getInstance().getItemTable().clear();

        serverOutput = null;
    }

    @When("^attempting to add an already existing title \"([^\"]*)\"$")
    public void attemptingToAddAnAlreadyExistingTitle(String arg0) throws Throwable {
        serverOutput = outputHandler.createTitle(String.format("%s,%s", arg0, "Title"));
    }

    @Given("^No title with ISBN \"([^\"]*)\" exists$")
    public void noTitleWithISBNExists(String arg0) throws Throwable {
        serverOutput = outputHandler.createItem(arg0);
        assertEquals("The Title Does Not Exists! Would you like to add it? (y/n)", serverOutput.getOutput());
    }

    @When("^Add item with isbn \"([^\"]*)\"$")
    public void attemptingToAddItemWithIsbn(String arg0) throws Throwable {
        serverOutput = outputHandler.createItem(arg0);
    }

    @And("^User \"([^\"]*)\" borrows copy (\\d+) of \"([^\"]*)\"$")
    public void userBorrowsCopyOf(String username, int copyNumber, String isbn) throws Throwable {
        serverOutput = outputHandler.borrowBook(String.format("%s,%s,%d", username, isbn, copyNumber));
    }

    @Then("^System outputs \"([^\"]*)\"$")
    public void receiveMessage(String expected) throws Throwable {
        assertEquals(expected, serverOutput.getOutput());
    }

    @And("^User \"([^\"]*)\" renews copy (\\d+) of \"([^\"]*)\"$")
    public void userRenewsCopyOf(String username, int copyNumber, String isbn) throws Throwable {
        serverOutput = outputHandler.renewBook(String.format("%s,%s,%d", username, isbn, copyNumber));
    }

    @And("^User \"([^\"]*)\" returns copy (\\d+) of \"([^\"]*)\"$")
    public void userReturnsCopyOf(String username, int copyNumber, String isbn) throws Throwable {
        serverOutput = outputHandler.returnBook(String.format("%s,%s,%d", username, isbn, copyNumber));
    }

    @When("^Remove copy (\\d+) of \"([^\"]*)\"$")
    public void removeCopyOf(int copyNumber, String isbn) throws Throwable {
        serverOutput = outputHandler.deleteItem(String.format("%s,%d", isbn, copyNumber));
    }

    @When("^Remove title \"([^\"]*)\"$")
    public void removeTitle(String isbn) throws Throwable {
        serverOutput = outputHandler.deleteTitle(isbn);
    }

    @When("^Remove user \"([^\"]*)\"$")
    public void removeUser(String username) throws Throwable {
        serverOutput = outputHandler.deleteUser(username);
    }
}

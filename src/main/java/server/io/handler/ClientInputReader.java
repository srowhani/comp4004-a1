package main.java.server.io.handler;

import main.java.server.io.handler.model.ClientState;
import main.java.server.io.handler.model.ServerOutput;

import static main.java.server.io.handler.model.ClientState.*;

public class ClientInputReader {
    private ClientInputHandler clientInputHandler;


    public ServerOutput processInput(String input, ClientState state) {
        String output;
        ServerOutput serverOutput = new ServerOutput();

        if (state == WAITING) {
            serverOutput.setOutput("Who Are you?Clerk or User?");
            serverOutput.setState(FINISHWAITING);
        } else if (state == FINISHWAITING) {

            if (input.equalsIgnoreCase("clerk")) {
                serverOutput.setOutput("Please Input The Password:");
                serverOutput.setState(CLERKLOGIN);
            } else if (input.equalsIgnoreCase("user")) {
                serverOutput.setOutput("Please Input Username and Password:'username,password'");
                serverOutput.setState(USERLOGIN);
            } else {
                serverOutput.setOutput("Who Are you?Clerk or User?");
                serverOutput.setState(FINISHWAITING);
            }

        } else if (state == CLERKLOGIN) {
            serverOutput = clientInputHandler.clerkLogin(input);
        } else if (state == USERLOGIN) {
            serverOutput = clientInputHandler.userLogin(input);
        } else if (state == CLERK) {
            if (input.equalsIgnoreCase("create user")) {
                serverOutput.setOutput("Please Input User Info:'username,password'");
                serverOutput.setState(CREATEUSER);
            } else if (input.equalsIgnoreCase("create title")) {
                serverOutput.setOutput("Please Input Title Info:'ISBN,title'");
                serverOutput.setState(CREATETITLE);
            } else if (input.equalsIgnoreCase("create item")) {
                serverOutput.setOutput("Please Input Item Info:'ISBN'");
                serverOutput.setState(CREATEITEM);
            } else if (input.equalsIgnoreCase("delete user")) {
                output = "Please Input User Info:'useremail'";
                state = DELETEUSER;
                serverOutput.setOutput(output);
                serverOutput.setState(state);
            } else if (input.equalsIgnoreCase("delete title")) {
                output = "Please Input Title Info:'ISBN'";
                state = DELETETITLE;
                serverOutput.setOutput(output);
                serverOutput.setState(state);
            } else if (input.equalsIgnoreCase("delete item")) {
                output = "Please Input Item Info:'ISBN,copynumber'";
                state = DELETEITEM;
                serverOutput.setOutput(output);
                serverOutput.setState(state);
            } else if (input.equalsIgnoreCase("log out")) {
                output = "Successfully Log Out!";
                state = WAITING;
                serverOutput.setOutput(output);
                serverOutput.setState(state);
            } else if (input.equalsIgnoreCase("main menu")) {
                output = "What can I do for you?Menu:Create User/Title/Item,Delete User/Title/Item.";
                state = CLERK;
                serverOutput.setOutput(output);
                serverOutput.setState(state);
            } else {
                output = "Please select from the menu.Menu:Create User/Title/Item,Delete User/Title/Item.";
                state = CLERK;
                serverOutput.setOutput(output);
                serverOutput.setState(state);
            }
        } else if (state == USER) {
            if (input.equalsIgnoreCase("borrow")) {
                output = "Please Input User Info:'useremail,ISBN,copynumber'";
                state = BORROW;
                serverOutput.setOutput(output);
                serverOutput.setState(state);
            } else if (input.equalsIgnoreCase("renew")) {
                output = "Please Input Title Info:'useremail,ISBN,copynumber'";
                state = RENEW;
                serverOutput.setOutput(output);
                serverOutput.setState(state);
            } else if (input.equalsIgnoreCase("return")) {
                output = "Please Input Item Info:'useremail,ISBN,copynumber'";
                state = RETURN;
                serverOutput.setOutput(output);
                serverOutput.setState(state);
            } else if (input.equalsIgnoreCase("pay fine")) {
                output = "Please Input User Info:'useremail'";
                state = PAYFINE;
                serverOutput.setOutput(output);
                serverOutput.setState(state);
            } else if (input.equalsIgnoreCase("log out")) {
                output = "Successfully Log Out!";
                state = WAITING;
                serverOutput.setOutput(output);
                serverOutput.setState(state);
            } else if (input.equalsIgnoreCase("main menu")) {
                output = "What can I do for you?Menu:Borrow,Renew,Return,Pay Fine.";
                state = USER;
                serverOutput.setOutput(output);
                serverOutput.setState(state);
            } else {
                output = "Please select from the menu.Menu:Borrow,Renew,Return,Pay Fine.";
                state = USER;
                serverOutput.setOutput(output);
                serverOutput.setState(state);
            }

        } else if (state == CREATEUSER) {
            if (input.equalsIgnoreCase("log out")) {
                output = "Successfully Log Out!";
                state = WAITING;
                serverOutput.setOutput(output);
                serverOutput.setState(state);
            } else if (input.equalsIgnoreCase("main menu")) {
                output = "What can I do for you?Menu:Create User/Title/Item,Delete User/Title/Item.";
                state = CLERK;
                serverOutput.setOutput(output);
                serverOutput.setState(state);
            } else {
                serverOutput = clientInputHandler.createUser(input);
            }
        } else if (state == CREATETITLE) {
            if (input.equalsIgnoreCase("log out")) {
                output = "Successfully Log Out!";
                state = WAITING;
                serverOutput.setOutput(output);
                serverOutput.setState(state);
            } else if (input.equalsIgnoreCase("main menu")) {
                output = "What can I do for you?Menu:Create User/Title/Item,Delete User/Title/Item.";
                state = CLERK;
                serverOutput.setOutput(output);
                serverOutput.setState(state);
            } else {
                serverOutput = clientInputHandler.createTitle(input);
            }
        } else if (state == CREATEITEM) {
            if (input.equalsIgnoreCase("log out")) {
                output = "Successfully Log Out!";
                state = WAITING;
                serverOutput.setOutput(output);
                serverOutput.setState(state);
            } else if (input.equalsIgnoreCase("main menu")) {
                output = "What can I do for you?Menu:Create User/Title/Item,Delete User/Title/Item.";
                state = CLERK;
                serverOutput.setOutput(output);
                serverOutput.setState(state);
            } else {
                serverOutput = clientInputHandler.createItem(input);
            }
        } else if (state == DELETEUSER) {
            if (input.equalsIgnoreCase("log out")) {
                output = "Successfully Log Out!";
                state = WAITING;
                serverOutput.setOutput(output);
                serverOutput.setState(state);
            } else if (input.equalsIgnoreCase("main menu")) {
                output = "What can I do for you?Menu:Create User/Title/Item,Delete User/Title/Item.";
                state = CLERK;
                serverOutput.setOutput(output);
                serverOutput.setState(state);
            } else {
                serverOutput = clientInputHandler.deleteUser(input);
            }
        } else if (state == DELETETITLE) {
            if (input.equalsIgnoreCase("log out")) {
                output = "Successfully Log Out!";
                state = WAITING;
                serverOutput.setOutput(output);
                serverOutput.setState(state);
            } else if (input.equalsIgnoreCase("main menu")) {
                output = "What can I do for you?Menu:Create User/Title/Item,Delete User/Title/Item.";
                state = CLERK;
                serverOutput.setOutput(output);
                serverOutput.setState(state);
            } else {
                serverOutput = clientInputHandler.deleteTitle(input);
            }
        } else if (state == DELETEITEM) {
            if (input.equalsIgnoreCase("log out")) {
                output = "Successfully Log Out!";
                state = WAITING;
                serverOutput.setOutput(output);
                serverOutput.setState(state);
            } else if (input.equalsIgnoreCase("main menu")) {
                output = "What can I do for you?Menu:Create User/Title/Item,Delete User/Title/Item.";
                state = CLERK;
                serverOutput.setOutput(output);
                serverOutput.setState(state);
            } else {
                serverOutput = clientInputHandler.deleteItem(input);
            }
        } else if (state == BORROW) {
            if (input.equalsIgnoreCase("log out")) {
                output = "Successfully Log Out!";
                state = WAITING;
                serverOutput.setOutput(output);
                serverOutput.setState(state);
            } else if (input.equalsIgnoreCase("main menu")) {
                output = "What can I do for you?Menu:Borrow,Renew,Return,Pay Fine.";
                state = USER;
                serverOutput.setOutput(output);
                serverOutput.setState(state);
            } else {
                serverOutput = clientInputHandler.borrowBook(input);
            }
        } else if (state == RENEW) {
            if (input.equalsIgnoreCase("log out")) {
                output = "Successfully Log Out!";
                state = WAITING;
                serverOutput.setOutput(output);
                serverOutput.setState(state);
            } else if (input.equalsIgnoreCase("main menu")) {
                output = "What can I do for you?Menu:Borrow,Renew,Return,Pay Fine.";
                state = USER;
                serverOutput.setOutput(output);
                serverOutput.setState(state);
            } else {
                serverOutput = clientInputHandler.renewBook(input);
            }
        } else if (state == RETURN) {
            if (input.equalsIgnoreCase("log out")) {
                output = "Successfully Log Out!";
                state = WAITING;
                serverOutput.setOutput(output);
                serverOutput.setState(state);
            } else if (input.equalsIgnoreCase("main menu")) {
                output = "What can I do for you?Menu:Borrow,Renew,Return,Pay Fine.";
                state = USER;
                serverOutput.setOutput(output);
                serverOutput.setState(state);
            } else {
                serverOutput = clientInputHandler.returnBook(input);
            }
        } else if (state == PAYFINE) {
            if (input.equalsIgnoreCase("log out")) {
                output = "Successfully Log Out!";
                state = WAITING;
                serverOutput.setOutput(output);
                serverOutput.setState(state);
            } else if (input.equalsIgnoreCase("main menu")) {
                output = "What can I do for you?Menu:Borrow,Renew,Return,Pay Fine.";
                state = USER;
                serverOutput.setOutput(output);
                serverOutput.setState(state);
            } else {
                serverOutput = clientInputHandler.payFine(input);
            }
        }
        return serverOutput;
    }

    public ClientInputReader pipe(ClientInputHandler clientInputHandler) {
        this.clientInputHandler = clientInputHandler;
        return this;
    }
}

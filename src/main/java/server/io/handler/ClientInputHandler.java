package main.java.server.io.handler;

import main.java.server.io.dao.UserTable;
import main.java.server.io.handler.model.ClientState;
import main.java.server.io.handler.model.ServerOutput;
import main.java.server.io.model.UserEntity;

import static main.java.server.io.handler.model.ClientState.CLERK;
import static main.java.server.io.handler.model.ClientState.CREATEUSER;

public class ClientInputHandler {
    public ServerOutput clerkLogin(String input) {
        return null;
    }

    public ServerOutput userLogin(String input) {
        return null;
    }

    public ServerOutput createUser(String csvUsernamePassword) {
        ServerOutput output = new ServerOutput("", ClientState.WAITING);
        String[] strArray= csvUsernamePassword.split(",");
        UserEntity result;
        if(strArray.length!=2){
            output.setOutput("Your input should in this format:'username,password'");
            output.setState(CREATEUSER);
        }else{
            result = UserTable.getInstance().add(strArray[0], strArray[1]);
            if(result != null) {
                output.setOutput("Success!");
            }else{
                output.setOutput("The User Already Exists!");
            }
            output.setState(CLERK);
        }
        return output;
    }

    public ServerOutput createTitle(String input) {
        return null;
    }

    public ServerOutput createItem(String input) {
        return null;
    }

    public ServerOutput deleteUser(String input) {
        return null;
    }

    public ServerOutput deleteTitle(String input) {
        return null;
    }

    public ServerOutput deleteItem(String input) {
        return null;
    }

    public ServerOutput borrowBook(String input) {
        return null;
    }

    public ServerOutput renewBook(String input) {
        return null;
    }

    public ServerOutput returnBook(String input) {
        return null;
    }

    public ServerOutput payFine(String input) {
        return null;
    }
}

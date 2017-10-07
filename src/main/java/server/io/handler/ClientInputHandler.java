package main.java.server.io.handler;

import main.java.server.io.dao.UserTable;
import main.java.server.io.error.LoanExistsException;
import main.java.server.io.error.OutstandingFeeExistsException;
import main.java.server.io.error.UserEntityNotFoundException;
import main.java.server.io.handler.model.ClientState;
import main.java.server.io.handler.model.ServerOutput;
import main.java.server.io.model.UserEntity;

import java.util.Optional;

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

    public ServerOutput deleteUser(String username) {
        ServerOutput output = new ServerOutput();
        Optional<UserEntity> userEntityOptional = UserTable.getInstance().lookup(userEntity -> userEntity.getUsername().equals(username));

        if (!userEntityOptional.isPresent()) {
            output.setOutput("The User Does Not Exist!");
            output.setState(CREATEUSER);
        } else {
            try {
                UserTable.getInstance().remove(userEntityOptional.get().getId());
                output.setOutput("Success!");
            } catch (Exception e) {
                output.setOutput(e.getMessage());
            }
            output.setState(CLERK);
        }
        return output;
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

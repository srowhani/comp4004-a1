package main.java.server.io.handler;

import main.java.server.io.handler.model.ClientState;
import main.java.server.io.handler.model.Output;
import main.java.server.io.handler.model.ServerOutput;

import static main.java.server.io.handler.model.ClientState.*;

public class ClientInputReader {
    private ClientInputHandler clientInputHandler;


    public ServerOutput processInput(String input, ClientState state) {
        String output = "";
        Output serverOutput = new ServerOutput();

        if (state == WAITING) {
            output = "Who Are you?Clerk or User?";
            state = FINISHWAITING;
            serverOutput.setOutput(output);
            serverOutput.setState(state);
        }else if (state == FINISHWAITING) {
            if (input.equalsIgnoreCase("clerk")) {
                output="Please Input The Password:";
                state=CLERKLOGIN;
                serverOutput.setOutput(output);
                serverOutput.setState(state);
            }else if (input.equalsIgnoreCase("user")) {
                output="Please Input Username and Password:'username,password'";
                state=USERLOGIN;
                serverOutput.setOutput(output);
                serverOutput.setState(state);
            }else{
                output = "Who Are you?Clerk or User?";
                state = FINISHWAITING;
                serverOutput.setOutput(output);
                serverOutput.setState(state);
            }
        }else if(state==CLERKLOGIN){
            o=clientInputHandler.clerkLogin(input);

            output=o.getOutput();
            state=o.getState();
            serverOutput.setOutput(output);
            serverOutput.setState(state);
        }else if(state==USERLOGIN){
            o=clientInputHandler.userLogin(input);
            output=o.getOutput();
            state=o.getState();
            serverOutput.setOutput(output);
            serverOutput.setState(state);
        }else if (state==CLERK){
            if (input.equalsIgnoreCase("create user")) {
                output = "Please Input User Info:'username,password'";
                state=CREATEUSER;
                serverOutput.setOutput(output);

                serverOutput.setState(state);
            }else if (input.equalsIgnoreCase("create title")) {
                output = "Please Input Title Info:'ISBN,title'";
                state=CREATETITLE;
                serverOutput.setOutput(output);
                serverOutput.setState(state);
            }else if (input.equalsIgnoreCase("create item")) {
                output = "Please Input Item Info:'ISBN'";
                state=CREATEITEM;
                serverOutput.setOutput(output);
                serverOutput.setState(state);
            }else if (input.equalsIgnoreCase("delete user")) {
                output = "Please Input User Info:'useremail'";
                state=DELETEUSER;
                serverOutput.setOutput(output);
                serverOutput.setState(state);
            }else if (input.equalsIgnoreCase("delete title")) {
                output = "Please Input Title Info:'ISBN'";
                state=DELETETITLE;
                serverOutput.setOutput(output);
                serverOutput.setState(state);
            }else if (input.equalsIgnoreCase("delete item")) {
                output = "Please Input Item Info:'ISBN,copynumber'";
                state=DELETEITEM;
                serverOutput.setOutput(output);
                serverOutput.setState(state);
            }else if(input.equalsIgnoreCase("log out")){
                output = "Successfully Log Out!";
                state = WAITING;
                serverOutput.setOutput(output);
                serverOutput.setState(state);
            }else if(input.equalsIgnoreCase("main menu")){
                output = "What can I do for you?Menu:Create User/Title/Item,Delete User/Title/Item.";
                state = CLERK;
                serverOutput.setOutput(output);
                serverOutput.setState(state);
            }else{
                output = "Please select from the menu.Menu:Create User/Title/Item,Delete User/Title/Item.";
                state = CLERK;
                serverOutput.setOutput(output);
                serverOutput.setState(state);
            }
        }else if (state==USER){
            if (input.equalsIgnoreCase("borrow")) {
                output = "Please Input User Info:'useremail,ISBN,copynumber'";
                state=BORROW;
                serverOutput.setOutput(output);
                serverOutput.setState(state);
            }else if (input.equalsIgnoreCase("renew")) {
                output = "Please Input Title Info:'useremail,ISBN,copynumber'";
                state=RENEW;
                serverOutput.setOutput(output);
                serverOutput.setState(state);
            }else if (input.equalsIgnoreCase("return")) {
                output = "Please Input Item Info:'useremail,ISBN,copynumber'";
                state=RETURN;
                serverOutput.setOutput(output);
                serverOutput.setState(state);
            }else if (input.equalsIgnoreCase("pay fine")) {
                output = "Please Input User Info:'useremail'";
                state=PAYFINE;
                serverOutput.setOutput(output);
                serverOutput.setState(state);
            }else if(input.equalsIgnoreCase("log out")){
                output = "Successfully Log Out!";
                state = WAITING;
                serverOutput.setOutput(output);
                serverOutput.setState(state);
            }else if(input.equalsIgnoreCase("main menu")){
                output = "What can I do for you?Menu:Borrow,Renew,Return,Pay Fine.";
                state = USER;
                serverOutput.setOutput(output);
                serverOutput.setState(state);
            }else{
                output = "Please select from the menu.Menu:Borrow,Renew,Return,Pay Fine.";
                state = USER;
                serverOutput.setOutput(output);
                serverOutput.setState(state);
            }

        }else if(state==CREATEUSER){
            if(input.equalsIgnoreCase("log out")){
                output = "Successfully Log Out!";
                state = WAITING;
                serverOutput.setOutput(output);
                serverOutput.setState(state);
            }else if(input.equalsIgnoreCase("main menu")){
                output = "What can I do for you?Menu:Create User/Title/Item,Delete User/Title/Item.";
                state = CLERK;
                serverOutput.setOutput(output);
                serverOutput.setState(state);
            }else{
                o=clientInputHandler.createUser(input);
                output=o.getOutput();
                state=o.getState();
                serverOutput.setOutput(output);
                serverOutput.setState(state);
            }
        }else if(state==CREATETITLE){
            if(input.equalsIgnoreCase("log out")){
                output = "Successfully Log Out!";
                state = WAITING;
                serverOutput.setOutput(output);
                serverOutput.setState(state);
            }else if(input.equalsIgnoreCase("main menu")){
                output = "What can I do for you?Menu:Create User/Title/Item,Delete User/Title/Item.";
                state = CLERK;
                serverOutput.setOutput(output);
                serverOutput.setState(state);
            }else{
                o=clientInputHandler.createTitle(input);
                output=o.getOutput();
                state=o.getState();
                serverOutput.setOutput(output);
                serverOutput.setState(state);
            }
        }else if(state==CREATEITEM){
            if(input.equalsIgnoreCase("log out")){
                output = "Successfully Log Out!";
                state = WAITING;
                serverOutput.setOutput(output);
                serverOutput.setState(state);
            }else if(input.equalsIgnoreCase("main menu")){
                output = "What can I do for you?Menu:Create User/Title/Item,Delete User/Title/Item.";
                state = CLERK;
                serverOutput.setOutput(output);
                serverOutput.setState(state);
            }else{
                o=clientInputHandler.createItem(input);
                output=o.getOutput();
                state=o.getState();
                serverOutput.setOutput(output);
                serverOutput.setState(state);
            }
        }else if(state==DELETEUSER){
            if(input.equalsIgnoreCase("log out")){
                output = "Successfully Log Out!";
                state = WAITING;
                serverOutput.setOutput(output);
                serverOutput.setState(state);
            }else if(input.equalsIgnoreCase("main menu")){
                output = "What can I do for you?Menu:Create User/Title/Item,Delete User/Title/Item.";
                state = CLERK;
                serverOutput.setOutput(output);
                serverOutput.setState(state);
            }else{
                o=clientInputHandler.deleteUser(input);
                output=o.getOutput();
                state=o.getState();
                serverOutput.setOutput(output);
                serverOutput.setState(state);
            }
        }else if(state==DELETETITLE){
            if(input.equalsIgnoreCase("log out")){
                output = "Successfully Log Out!";
                state = WAITING;
                serverOutput.setOutput(output);
                serverOutput.setState(state);
            }else if(input.equalsIgnoreCase("main menu")){
                output = "What can I do for you?Menu:Create User/Title/Item,Delete User/Title/Item.";
                state = CLERK;
                serverOutput.setOutput(output);
                serverOutput.setState(state);
            }else{
                o=clientInputHandler.deleteTitle(input);
                output=o.getOutput();
                state=o.getState();
                serverOutput.setOutput(output);
                serverOutput.setState(state);
            }
        }else if(state==DELETEITEM){
            if(input.equalsIgnoreCase("log out")){
                output = "Successfully Log Out!";
                state = WAITING;
                serverOutput.setOutput(output);
                serverOutput.setState(state);
            }else if(input.equalsIgnoreCase("main menu")){
                output = "What can I do for you?Menu:Create User/Title/Item,Delete User/Title/Item.";
                state = CLERK;
                serverOutput.setOutput(output);
                serverOutput.setState(state);
            }else{
                o=clientInputHandler.deleteItem(input);
                output=o.getOutput();
                state=o.getState();
                serverOutput.setOutput(output);
                serverOutput.setState(state);
            }
        }else if(state==BORROW){
            if(input.equalsIgnoreCase("log out")){
                output = "Successfully Log Out!";
                state = WAITING;
                serverOutput.setOutput(output);
                serverOutput.setState(state);
            }else if(input.equalsIgnoreCase("main menu")){
                output = "What can I do for you?Menu:Borrow,Renew,Return,Pay Fine.";
                state = USER;
                serverOutput.setOutput(output);
                serverOutput.setState(state);
            }else{
                o=clientInputHandler.borrow(input);
                output=o.getOutput();
                state=o.getState();
                serverOutput.setOutput(output);
                serverOutput.setState(state);
            }
        }else if(state==RENEW){
            if(input.equalsIgnoreCase("log out")){
                output = "Successfully Log Out!";
                state = WAITING;
                serverOutput.setOutput(output);
                serverOutput.setState(state);
            }else if(input.equalsIgnoreCase("main menu")){
                output = "What can I do for you?Menu:Borrow,Renew,Return,Pay Fine.";
                state = USER;
                serverOutput.setOutput(output);
                serverOutput.setState(state);
            }else{
                o=clientInputHandler.renew(input);
                output=o.getOutput();
                state=o.getState();
                serverOutput.setOutput(output);
                serverOutput.setState(state);
            }
        }else if(state==RETURN){
            if(input.equalsIgnoreCase("log out")){
                output = "Successfully Log Out!";
                state = WAITING;
                serverOutput.setOutput(output);
                serverOutput.setState(state);
            }else if(input.equalsIgnoreCase("main menu")){
                output = "What can I do for you?Menu:Borrow,Renew,Return,Pay Fine.";
                state = USER;
                serverOutput.setOutput(output);
                serverOutput.setState(state);
            }else{
                o=clientInputHandler.returnBserverOutput.(input);
                output=o.getOutput();
                state=o.getState();
                serverOutput.setOutput(output);
                serverOutput.setState(state);
            }
        }else if(state==PAYFINE){
            if(input.equalsIgnoreCase("log out")){
                output = "Successfully Log Out!";
                state = WAITING;
                serverOutput.setOutput(output);
                serverOutput.setState(state);
            }else if(input.equalsIgnoreCase("main menu")){
                output = "What can I do for you?Menu:Borrow,Renew,Return,Pay Fine.";
                state = USER;
                serverOutput.setOutput(output);
                serverOutput.setState(state);
            }else{
                o=clientInputHandler.payFine(input);
                output=o.getOutput();
                state=o.getState();
                serverOutput.setOutput(output);
                serverOutput.setState(state);
            }
        }
        return serverOutput.
    }

    public ClientInputReader pipe (ClientInputHandler clientInputHandler) {
        this.clientInputHandler = clientInputHandler;
        return this;
    }
}

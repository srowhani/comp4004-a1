package main.java.server.io.handler;

import main.java.server.io.dao.*;
import main.java.server.io.error.*;
import main.java.server.io.handler.model.ClientState;
import main.java.server.io.handler.model.ServerOutput;
import main.java.server.io.model.UserEntity;
import main.java.util.Config;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import static main.java.server.io.handler.model.ClientState.*;

public class ClientInputHandler {
    public static boolean isInteger(String value) {
        char[] ch = value.toCharArray();
        boolean isNumber = true;
        if (value.length() == 13) {
            for (int i = 0; i < ch.length; i++) {
                isNumber = Character.isDigit(ch[i]);
            }
        } else {
            isNumber = false;
        }
        return isNumber;
    }

    public ServerOutput clerkLogin(String clerkPassword) {
        ServerOutput output = new ServerOutput();
        if (clerkPassword.equalsIgnoreCase(Config.CLERK_PASSWORD)) {
            output.setOutput("What can I do for you?Menu:Create User/Title/Item,Delete User/Title/Item.");
            output.setState(CLERK);
        } else {
            output.setOutput("Wrong Password!Please Input The Password:");
            output.setState(CLERKLOGIN);
        }
        return output;
    }

    public ServerOutput userLogin(String input) {
        ServerOutput output = new ServerOutput();
        String[] strArray = input.split(",");

        if (strArray.length != 2) {
            output.setOutput("Your input should in this format:'username,password'");
            output.setState(USERLOGIN);
        } else {
            try {
                UserTable.getInstance().validate(strArray[0], strArray[1]);
                output.setOutput("What can I do for you?Menu:Borrow,Renew,Return,Pay Fine.");
                output.setState(USER);
            } catch (InvalidUserCredentialsException e) {
                output.setOutput("Wrong Password!Please Input Username and Password:'username,password'");
                output.setState(USERLOGIN);
            } catch (UserEntityNotFoundException e) {
                output.setOutput("The User Does Not Exist!Please The Username and Password:'username,password'");
                output.setState(USERLOGIN);
            }
        }
        return output;
    }

    public ServerOutput createUser(String csvUsernamePassword) {
        ServerOutput output = new ServerOutput("", ClientState.WAITING);
        String[] strArray = csvUsernamePassword.split(",");

        if (strArray.length != 2) {
            output.setOutput("Your input should in this format:'username,password'");
            output.setState(CREATEUSER);
        } else {
            try {
                UserTable.getInstance().add(strArray[0], strArray[1]);
                output.setOutput("Success!");
            } catch (UserEntityExistsException e) {
                output.setOutput("The User Already Exists!");
            }
            output.setState(CLERK);
        }
        return output;
    }

    public ServerOutput createTitle(String input) {
        ServerOutput output = new ServerOutput();
        String[] strArray = input.split(",");
        boolean number = isInteger(strArray[0]);

        if (strArray.length != 2 || number != true) {
            output.setOutput("Your input should in this format:'ISBN,title',ISBN should be a 13-digit number");
            output.setState(CREATETITLE);
        } else {
            try {
                TitleTable.getInstance().createTitle(strArray[0], strArray[1]);
                output.setOutput("Success!");
            } catch (TitleEntityExistsException e) {
                output.setOutput("The Title Already Exists!");
            }
            output.setState(CLERK);
        }
        return output;
    }

    public ServerOutput createItem(String input) {
        ServerOutput output = new ServerOutput();
        String[] strArray = input.split(",");
        boolean number = isInteger(strArray[0]);
        Object result = "";
        if (strArray.length != 1 || number != true) {
            output.setOutput("Your input should in this format:'ISBN',ISBN should be a 13-digit number");
            output.setState(CREATEITEM);
        } else {
            try {
                ItemTable.getInstance().addItem(strArray[0]);
                output.setOutput("Success!");
                output.setState(CLERK);
            } catch (NoSuchISBNExistsException e) {
                output.setOutput("The Title Does Not Exists! Would you like to add it? (y/n)");
                output.setState(CONFIRM_ADD_TITLE);
            }
        }
        return output;
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

    public ServerOutput deleteTitle(String isbn) {
        ServerOutput output = new ServerOutput();

        if (isbn.length() == 13) {
            try {
                Long.valueOf(isbn);
                TitleTable.getInstance().remove(isbn);
                output.setOutput("Success!");
                output.setState(CLERK);
            } catch (NumberFormatException e) {
                output.setOutput("Your input should consist only of numbers: " + e.getMessage());
                output.setState(DELETETITLE);
            } catch (Exception e) {
                output.setOutput(e.getMessage());
                output.setState(DELETETITLE);
            }
        } else {
            output.setOutput("Your input should in this format:'ISBN',ISBN should be a 13-digit number");
            output.setState(DELETETITLE);
        }

        return output;
    }

    public ServerOutput deleteItem(String input) {
        ServerOutput output = new ServerOutput();
        String[] strArray = input.split(",");
        boolean number = isInteger(strArray[0]);
        Object result = "";
        if (strArray.length != 2 || number != true) {
            output.setOutput("Your input should in this format:'ISBN,copynumber',ISBN should be a 13-digit number");
            output.setState(DELETEITEM);
        } else {
            boolean copynumber = isNumber(strArray[1]);

            if (!copynumber) {
                output.setOutput("Your input should in this format:'ISBN,copynumber',ISBN should be a 13-digit number");
                output.setState(DELETEITEM);
            } else {
                try {
                    ItemTable.getInstance().delete(strArray[0], strArray[1]);
                    output.setOutput("Success!");
                } catch (Exception e) {
                    output.setOutput(e.getMessage());
                }
                output.setState(CLERK);
            }
        }
        return output;
    }

    public ServerOutput borrowBook(String csvUsernameIsbnCopyNumber) {
        ServerOutput output = new ServerOutput();
        String[] strArray = csvUsernameIsbnCopyNumber.split(",");
        Optional<UserEntity> userEntityOptional = UserTable.getInstance().lookup(
                user -> user.getUsername().equals(strArray[0]));
        Object result = "";
        if (strArray.length != 3) {
            output.setOutput("Your input should in this format:'useremail,ISBN,copynumber'");
            output.setState(BORROW);
        } else if (!userEntityOptional.isPresent()) {
            output.setOutput("The User Does Not Exist!");
            output.setState(BORROW);
        } else {
            boolean ISBN = isInteger(strArray[1]);
            boolean copynumber = isNumber(strArray[2]);
            if (!ISBN || !copynumber) {
                output.setOutput("Your input should in this format:'useremail,ISBN,copynumber'");
                output.setState(BORROW);
            } else {
                try {
                    LoanTable.getInstance().createLoan(userEntityOptional.get().getId(), strArray[1], strArray[2], new Date());
                    output.setOutput("Success!");
                } catch (Exception e) {
                    output.setOutput(e.getMessage());
                }
            }
            output.setState(USER);
        }
        return output;
    }

    public ServerOutput borrowBookAsClerk(String csvUsernameIsbnCopyNumber) {
        ServerOutput output = new ServerOutput();
        String[] strArray = csvUsernameIsbnCopyNumber.split(",");
        Optional<UserEntity> userEntityOptional = UserTable.getInstance().lookup(
                user -> user.getUsername().equals(strArray[0]));
        Object result = "";
        if (strArray.length != 3) {
            output.setOutput("Your input should in this format:'useremail,ISBN,copynumber'");
            output.setState(BORROW);
        } else if (!userEntityOptional.isPresent()) {
            output.setOutput("The User Does Not Exist!");
            output.setState(BORROW);
        } else {
            boolean ISBN = isInteger(strArray[1]);
            boolean copynumber = isNumber(strArray[2]);
            if (!ISBN || !copynumber) {
                output.setOutput("Your input should in this format:'useremail,ISBN,copynumber'");
                output.setState(BORROW);
            } else {
                try {
                    LoanTable.getInstance().createLoan(userEntityOptional.get().getId(), strArray[1], strArray[2], new Date());
                    output.setOutput("Success!");
                } catch (Exception e) {
                    output.setOutput(e.getMessage());
                }
            }
            output.setState(CLERK);
        }
        return output;
    }

    public ServerOutput renewBook(String csvUsernameIsbnCopyNumber) {
        ServerOutput output = new ServerOutput();
        String[] strArray = csvUsernameIsbnCopyNumber.split(",");
        Optional<UserEntity> userEntityOptional = UserTable.getInstance().lookup(user -> user.getUsername().equals(strArray[0]));

        Object result = "";
        if (strArray.length != 3) {
            output.setOutput("Your input should in this format:'useremail,ISBN,copynumber'");
            output.setState(RENEW);
        } else if (!userEntityOptional.isPresent()) {
            output.setOutput("The User Does Not Exist!");
            output.setState(RENEW);
        } else {
            boolean ISBN = isInteger(strArray[1]);
            boolean copynumber = isNumber(strArray[2]);
            if (!ISBN || !copynumber) {
                output.setOutput("Your input should in this format:'useremail,ISBN,copynumber'");
                output.setState(RENEW);
            } else {
                try {
                    LoanTable.getInstance().renewItem(userEntityOptional.get().getId(), strArray[1], strArray[2], new Date());
                    output.setOutput("Success!");
                } catch (Exception e) {
                    output.setOutput(e.getMessage());
                }
            }
            output.setState(USER);
        }
        return output;
    }

    public ServerOutput returnBook(String csvUsernameIsbnCopynumber) {
        ServerOutput output = new ServerOutput();
        String[] strArray = csvUsernameIsbnCopynumber.split(",");
        Optional<UserEntity> userEntityOptional = UserTable.getInstance().lookup(user -> user.getUsername().equals(strArray[0]));
        Object result = "";
        if (strArray.length < 3) {
            output.setOutput("Your input should in this format:'useremail,ISBN,copynumber'");
            output.setState(RETURN);
        } else if (!userEntityOptional.isPresent()) {
            output.setOutput("The User Does Not Exist!");
            output.setState(RETURN);
        } else {
            boolean ISBN = isInteger(strArray[1]);
            boolean copynumber = isNumber(strArray[2]);
            if (ISBN != true || copynumber != true) {
                output.setOutput("Your input should in this format:'useremail,ISBN,copynumber'");
                output.setState(RETURN);
            } else {
                try {
                    Date d = new Date();
                    if (strArray.length == 4) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(d);
                        calendar.add(Calendar.DAY_OF_YEAR, Integer.parseInt(strArray[3]));
                        d = calendar.getTime();
                    }
                    LoanTable.getInstance().returnItem(userEntityOptional.get().getId(), strArray[1], strArray[2], d);
                    output.setOutput("Success!");
                } catch (NoSuchLoanExistsException e) {
                    output.setOutput(e.getMessage());
                }
            }
            output.setState(USER);
        }
        return output;
    }

    public ServerOutput payFine(String username) {
        ServerOutput output = new ServerOutput();

        Optional<UserEntity> userEntityOptional = UserTable.getInstance().lookup(userEntity -> userEntity.getUsername().equals(username));

        if (username.length() == 0) {
            output.setOutput("Your input should in this format:'useremail'");
            output.setState(PAYFINE);
        } else if (!userEntityOptional.isPresent()) {
            output.setOutput("The User Does Not Exist!");
            output.setState(PAYFINE);
        } else {
            UserEntity userEntity = userEntityOptional.get();

            try {
                FeeTable.getInstance().payFine(userEntity.getId());
                output.setOutput("Success!");

            } catch (PendingLoansExistException e) {
                output.setOutput(e.getMessage());
            }
            output.setState(USER);
        }

        return output;
    }

    public boolean isNumber(String value) {
        char[] ch = value.toCharArray();
        boolean isNumber = true;
        for (int i = 0; i < ch.length; i++) {
            isNumber = Character.isDigit(ch[i]);
        }
        return isNumber;
    }
}

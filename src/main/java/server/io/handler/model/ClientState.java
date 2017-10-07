package main.java.server.io.handler.model;

public enum ClientState {
    WAITING,
    FINISHWAITING,
    CLERK,
    USER,
    CREATEUSER,
    CREATETITLE,
    CREATEITEM,
    DELETEUSER,
    DELETETITLE,
    DELETEITEM,
    BORROW,
    RENEW,
    RETURN,
    PAYFINE,
    CLERKLOGIN,
    USERLOGIN
}

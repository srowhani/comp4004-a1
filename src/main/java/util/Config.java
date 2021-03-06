package main.java.util;

public class Config {
    public static final int MAX_CLIENTS = 5;
    public static final int DEFAULT_PORT = 5050;
    public static final String DEFAULT_HOST = "127.0.0.1";
    public static final int MAX_BORROWED_ITEMS = 2;
    public static final int MAX_RENEWALS = 2;
    //1 minuter for 1 day
    public static final long SIMULATED_DAY = 1000 * 60 * 60 * 24;
    public static final int OVERDUE = 5;
    public static final String CLERK_PASSWORD = "admin";

}

package main.java.server.io.error;

public class MaximumBorrowedItemsExceededException extends Exception {
    @Override
    public String getMessage () {
        return "Maximum Borrowed Items Exceeded!";
    }
}

package util;

/**
 * util.Assertions Class
 * Use of failing runnable to assert object instantiation works as expected.
 */
public class Assert {
    public static void assertDoesNotThrow(FailingRunnable action){
        try{
            action.run();
        }
        catch(Exception ex){
            throw new Error(ex);
        }
    }
}


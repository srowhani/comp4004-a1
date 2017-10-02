package lib;

@FunctionalInterface
public interface FailingRunnable {
    void run() throws Exception;
}


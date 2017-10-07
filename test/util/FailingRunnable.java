package util;

@FunctionalInterface
public interface FailingRunnable {
    void run() throws Exception;
}


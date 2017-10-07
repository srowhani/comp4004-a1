package main.java.util;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Trace {
    private static Trace _instance = null;

    public Logger getLogger(Object o) {
        return Logger.getLogger(o.getClass().getName());
    }

    private Trace() {
        String userDir = System.getProperty("user.dir");
        System.out.println(userDir);
        String configFile = String.format("%s/%s/%s.properties", userDir, "src/main/resources", "log4j");
        PropertyConfigurator.configure(configFile);
    }

    public static Trace getInstance() {
        if (_instance == null) {
            synchronized (Trace.class) {
                _instance = new Trace();
            }
        }
        return _instance;
    }
}
package com.king.scoreboard.properties;


/**
 * Created by ioannis.metaxas on 2015-11-29.
 *
 * Initializes all the properties managers
 */
public class PropertiesManager {

    private static volatile PropertiesManager instance = null;
    private final static Object lock = new Object();

    private LoggingPropertiesManager loggingPropertiesManager;
    private ServerPropertiesManager serverPropertiesManager;
    private ConfigurationManager configurationManager;

    private PropertiesManager(){
        loggingPropertiesManager = LoggingPropertiesManager.getInstance();
        serverPropertiesManager = ServerPropertiesManager.getInstance();
        configurationManager = ConfigurationManager.getInstance();
    }

    /**
     * Creates or reuses the manager's instance.
     * Ensures that only a Singleton instance is used.
     *
     * @return the manager's instance.
     */
    public static PropertiesManager getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null)
                    instance = new PropertiesManager();
            }
        }
        return instance;
    }

    public static void init() {
        getInstance();
    }

    /**
     * Returns a server property from the given key
     * @param key which corresponds to a server property
     * @return a server property from the given key
     */
    public String getServerProperty(String key) {
        return serverPropertiesManager.getProperty(key);
    }

    /**
     * Returns a configuration property from the given key
     * @param key which corresponds to a configuration property
     * @return a configuration property from the given key
     */
    public String getConfigProperty(String key) {
        return configurationManager.getProperty(key);
    }
}

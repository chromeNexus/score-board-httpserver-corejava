package com.king.scoreboard;

import com.king.scoreboard.properties.ConfigurationManager;
import com.king.scoreboard.properties.LoggingPropertiesManager;
import com.king.scoreboard.properties.PropertiesManager;
import com.king.scoreboard.properties.ServerPropertiesManager;
import org.junit.Test;
import static org.junit.Assert.assertSame;

/**
 * Created by ioannis.metaxas on 2015-12-02.
 */
public class PropertiesManagersTest {

    @Test
    public void testSingletonInstances() {
        assertSame(PropertiesManager.getInstance(), PropertiesManager.getInstance());
        assertSame(ServerPropertiesManager.getInstance(), ServerPropertiesManager.getInstance());
        assertSame(LoggingPropertiesManager.getInstance(), LoggingPropertiesManager.getInstance());
        assertSame(ConfigurationManager.getInstance(), ConfigurationManager.getInstance());
    }
}

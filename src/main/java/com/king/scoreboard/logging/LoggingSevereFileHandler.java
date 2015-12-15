package com.king.scoreboard.logging;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogRecord;

/**
 * Created by ioannis.metaxas on 2015-11-29.
 *
 * Publishes log records with level SEVERE
 */
public class LoggingSevereFileHandler extends FileHandler {

    public LoggingSevereFileHandler() throws IOException, SecurityException {
        super();
    }

    @Override
    public void publish(final LogRecord logRecord) {
        if (logRecord.getLevel().equals(Level.SEVERE)) {
            super.publish(logRecord);
        }
    }
}

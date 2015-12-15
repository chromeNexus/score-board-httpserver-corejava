package com.king.scoreboard.handler;

import com.king.scoreboard.properties.ConfigProperties;
import com.king.scoreboard.service.ServicesEnum;
import com.king.scoreboard.userscore.UserScoreManager;
import com.king.scoreboard.util.HttpCodes;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * Created by ioannis.metaxas on 2015-11-30.
 *
 * Handles the get-high-score-list requests.
 */
public class HighScoreListHandler extends AbstractBaseHandler {

    private static final Logger LOGGER = Logger.getLogger("confLogger");

    private int levelId;

    public HighScoreListHandler(int levelId) {
        this.levelId = levelId;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        long startTime = System.nanoTime();
        LOGGER.info("GET-HIGHSCORE-LIST SERVICE CALL (levelId=" + levelId + ")");

        String response = UserScoreManager.getInstance().getHighScoreList(levelId, Integer.parseInt(ConfigProperties.MAX_HIGH_SCORES_RETURNED.getValue()));

        httpExchange.sendResponseHeaders(HttpCodes.OK.getCode(), response.length());
        httpExchange.getResponseBody().write(response.getBytes());

        LOGGER.info("GET-HIGHSCORE-LIST SERVICE CALL (levelId=" + levelId + ") RETURNS: response=" + response + ", Took: " + (System.nanoTime() - startTime) / 1000000 + " ms");
    }

    @Override
    public ServicesEnum getService() {
        return ServicesEnum.HIGHSCORE_LIST;
    }
}

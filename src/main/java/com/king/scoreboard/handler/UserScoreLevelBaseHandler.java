package com.king.scoreboard.handler;

import com.king.scoreboard.service.ServicesEnum;
import com.king.scoreboard.session.SessionManager;
import com.king.scoreboard.userscore.ScoreListUtil;
import com.king.scoreboard.userscore.UserScore;
import com.king.scoreboard.userscore.UserScoreManager;
import com.king.scoreboard.util.HttpCodes;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * Created by ioannis.metaxas on 2015-11-30.
 *
 * Handles the user-score level requests.
 * Returns Http Code 400 if the score is a not supported value
 */
public class UserScoreLevelBaseHandler extends AbstractBaseHandler {

    private static final Logger LOGGER = Logger.getLogger("confLogger");

    private int levelId;
    private String sessionKey;
    private String score;

    public UserScoreLevelBaseHandler(int levelId, String sessionKey, String score) {
        this.levelId = levelId;
        this.sessionKey = sessionKey;
        this.score = score;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        long startTime = System.nanoTime();

        LOGGER.info("USER_SCORE_LEVEL SERVICE CALL (levelId=" + levelId + ")");

        if(ScoreListUtil.isScoreValidated(score)){
            String userId = SessionManager.getInstance().getUserSession(sessionKey).getUserId().toString();
            UserScoreManager.getInstance().putUserScore(levelId, new UserScore(userId, Integer.parseInt(score)));

            httpExchange.sendResponseHeaders(HttpCodes.OK.getCode(), 0);

            LOGGER.info("USER_SCORE_LEVEL SERVICE CALL (levelId=" + levelId + ", sessionKey=" + sessionKey + ", score=" + score + ") " + "RETURNS: " + HttpCodes.OK.toString() + ", Took: " + (System.nanoTime() - startTime) / 1000000 + " ms");
        } else {
            httpExchange.sendResponseHeaders(HttpCodes.BAD_REQUEST.getCode(), -1);
            LOGGER.info("USER_SCORE_LEVEL SERVICE CALL (levelId=" + levelId + ", sessionKey=" + sessionKey + ", score=" + score + ") " + "RETURNS: " + HttpCodes.BAD_REQUEST.toString() + ", Took: " + (System.nanoTime() - startTime) / 1000000 + " ms");
        }
    }

    @Override
    public ServicesEnum getService() {
        return ServicesEnum.USERSCORE_LEVEL;
    }

    @Override
    public String toString() {
        return "UserScoreLevelBaseHandler{" +
                "levelId=" + levelId +
                ", sessionKey='" + sessionKey + '\'' +
                ", score=" + score +
                '}';
    }
}

package com.king.scoreboard.handler;

import com.king.scoreboard.service.ServicesEnum;
import com.king.scoreboard.session.SessionManager;
import com.king.scoreboard.util.HttpCodes;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * Created by ioannis.metaxas on 2015-11-30.
 *
 * Handles the login requests.
 */
public class LoginHandler extends AbstractBaseHandler {

    private static final Logger LOGGER = Logger.getLogger("confLogger");

    private int userId;

    public LoginHandler(int userId) {
        this.userId = userId;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        long startTime = System.nanoTime();
        LOGGER.info("LOGIN SERVICE CALL (userId=" + userId + ")");

        String sessionKey = SessionManager.getInstance().createUserSession(userId).getSessionKey();

        httpExchange.sendResponseHeaders(HttpCodes.OK.getCode(), sessionKey.length());
        httpExchange.getResponseBody().write(sessionKey.getBytes());

        LOGGER.info("LOGIN SERVICE CALL (userId=" + userId + ") RETURNS: sessionKey=" + sessionKey + ", Took: " + (System.nanoTime() - startTime) / 1000000 + " ms");
    }

    @Override
    public ServicesEnum getService() {
        return ServicesEnum.LOGIN;
    }
}

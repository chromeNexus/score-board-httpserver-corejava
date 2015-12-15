package com.king.scoreboard.server;

import com.king.scoreboard.handler.BaseHandler;
import com.king.scoreboard.handler.HighScoreListHandler;
import com.king.scoreboard.handler.LoginHandler;
import com.king.scoreboard.handler.UserScoreLevelBaseHandler;
import com.king.scoreboard.service.ServicesEnum;
import com.king.scoreboard.userscore.ScoreListUtil;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

/**
 * Created by ioannis.metaxas on 2015-11-28.
 *
 * The http server handler for handling requests.
 * When a request has reached this point, it is sure that is a valid one.
 *
 */
public class ScoreboardHttpHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        try {
            BaseHandler handler = null;
            String requestedUri = httpExchange.getRequestURI().toString();
            int id = Integer.parseInt(requestedUri.split("/")[1]);
            if (ServicesEnum.isValidServiceUri(requestedUri, ServicesEnum.LOGIN)) {
                handler = new LoginHandler(id);
            } else if (ServicesEnum.isValidServiceUri(requestedUri, ServicesEnum.HIGHSCORE_LIST)) {
                handler = new HighScoreListHandler(id);
            } else if (ServicesEnum.isValidServiceUri(requestedUri, ServicesEnum.USERSCORE_LEVEL)) {
                String sessionKey = requestedUri.split("=")[1];
                String score = ScoreListUtil.getScore(httpExchange.getRequestBody());
                handler = new UserScoreLevelBaseHandler(id, sessionKey, score);
            }
            handler.handle(httpExchange);
        } finally {
            httpExchange.getResponseBody().close();
        }
    }
}

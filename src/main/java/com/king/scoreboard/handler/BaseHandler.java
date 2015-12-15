package com.king.scoreboard.handler;

import com.king.scoreboard.service.ServicesEnum;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

/**
 * Created by ioannis.metaxas on 2015-11-30.
 *
 * The interface of all the service handlers
 */
public interface BaseHandler {

    ServicesEnum getService();

    void handle(HttpExchange httpExchange) throws IOException;
}

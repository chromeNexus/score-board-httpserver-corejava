package com.king.scoreboard.server;


import com.king.scoreboard.properties.ConfigProperties;
import com.king.scoreboard.properties.ServerProperties;
import com.king.scoreboard.service.ServiceFilter;
import com.king.scoreboard.session.SessionManager;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * Created by ioannis.metaxas on 2015-11-28.
 *
 * The score-board server implementation
 */
public class ScoreboardServer {

    private HttpServer httpServer;
    private ExecutorService serverExecutor;

    public ScoreboardServer() throws IOException {

        // Create the server
        httpServer = HttpServer.create(new InetSocketAddress(InetAddress.getByName(ConfigProperties.BASE_URI.getValue()), ServerProperties.SERVER_PORT.getValue()), ServerProperties.SERVER_BACKLOG.getValue());
        HttpContext context = httpServer.createContext("/", new ScoreboardHttpHandler());

        // Add the endpoint filter
        context.getFilters().add(new ServiceFilter());

        // Set an Executor for the multi-threading
        serverExecutor = new ThreadPoolExecutor(ServerProperties.HTTP_POOL_CONNECTIONS.getValue(),
                ServerProperties.HTTP_MAX_CONNECTIONS.getValue(),
                ServerProperties.DELAY_FOR_TERMINATION.getValue(),
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(ServerProperties.HTTP_QUEUE_MAX_ITEMS.getValue()),
                new ThreadPoolExecutor.AbortPolicy());

        httpServer.setExecutor(serverExecutor);
    }

    /**
     * Starts the server
     */
    public void start() {
        httpServer.start();
        SessionManager.init();
        Runtime.getRuntime().addShutdownHook(new ShutdownScoreboardServerHook(httpServer, SessionManager.getInstance().getTimer()));
        printHelp();
    }

    /**
     * Stops the server
     */
    public void stop() {
        if (this.httpServer != null) {
            this.httpServer.stop(0);
            this.serverExecutor.shutdown();
            SessionManager.getInstance().getTimer().cancel();
        }
    }

    /**
     * Prints the current service endpoints
     */
    private static void printHelp(){
        System.out.println("\nSupported Operations:");
        System.out.println("---------------------");
        System.out.println("GET http://" + ConfigProperties.BASE_URI.getValue() + ":" + ServerProperties.SERVER_PORT.getValue() + "/<userid>/login");
        System.out.println("POST http://" + ConfigProperties.BASE_URI.getValue() + ":" + ServerProperties.SERVER_PORT.getValue() + "/<levelid>/score?sessionkey=<sessionkey>&score=<score>");
        System.out.println("GET http://" + ConfigProperties.BASE_URI.getValue() + ":" + ServerProperties.SERVER_PORT.getValue() + "/<levelid>/highscorelist");
    }
}

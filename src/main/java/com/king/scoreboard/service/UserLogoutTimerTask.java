package com.king.scoreboard.service;

import com.king.scoreboard.session.SessionManager;

import java.util.TimerTask;

/**
 * Created by ioannis.metaxas on 2015-11-29.
 *
 * A task for removing user sessions.
 */
public class UserLogoutTimerTask extends TimerTask {

    public final long LOGOUT_TIMEOUT;

    public UserLogoutTimerTask(long logoutTimeout) {
        this.LOGOUT_TIMEOUT = logoutTimeout;
    }

    @Override
    public void run() {
        SessionManager.getInstance().removeUserSessions(LOGOUT_TIMEOUT);
    }
}

package com.king.scoreboard.userscore;

import java.util.Comparator;

/**
 * Created by ioannis.metaxas on 2015-12-01.
 *
 * Ensures the ascending score order in the user scores map.
 */
public class UserScoreComparator implements Comparator<UserScore> {

    @Override
    public int compare(UserScore userScore1, UserScore userScore2) {
        return (userScore1.getScore() < userScore2.getScore() ) ? -1 : (userScore1.getScore() > userScore2.getScore() ) ? 1 : 0 ;
    }
}

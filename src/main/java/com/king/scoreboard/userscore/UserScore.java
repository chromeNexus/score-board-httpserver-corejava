package com.king.scoreboard.userscore;

/**
 * Created by ioannis.metaxas on 2015-11-28.
 *
 * Model of the user score relation
 */
public class UserScore {

    private String userId;
    private Integer score;

    public UserScore(String userId, Integer score) {
        this.userId = userId;
        this.score = score;
    }

    public String getUserId() {
        return userId;
    }

    public Integer getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "UserScore{" +
                "userId='" + userId + '\'' +
                ", score=" + score +
                '}';
    }
}

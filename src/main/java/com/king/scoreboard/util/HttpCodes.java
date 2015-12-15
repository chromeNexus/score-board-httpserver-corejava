package com.king.scoreboard.util;

/**
 * Created by ioannis.metaxas on 2015-12-01.
 *
 * The used Http codes enumeration
 */
public enum HttpCodes {

    OK(200), BAD_REQUEST(400);

    private int code;

    HttpCodes(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    @Override
    public String toString() {
        return "HttpCode{" + name() + " " + code + '}';
    }
}

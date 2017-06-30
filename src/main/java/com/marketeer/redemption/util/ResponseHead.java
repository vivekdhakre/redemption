package com.marketeer.redemption.util;

/**
 * Created by ahoy on 1/5/17.
 */
public class ResponseHead {

    private String head;

    private String message;

    public ResponseHead(String head, String message) {
        this.head = head;
        this.message = message;
    }

    public String getHead() {
        return head;
    }

    public String getMessage() {
        return message;
    }
}

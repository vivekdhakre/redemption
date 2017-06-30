package com.marketeer.redemption.util;

/**
 * Created by Vivek on 27/4/17.
 */
public class Header {
    Integer code;
    String message;

    public Header(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

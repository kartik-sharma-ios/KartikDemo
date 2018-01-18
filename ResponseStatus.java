package com.dimts.eticketing.model.Response;

/**
 * Created by Heena Aggarwal on 17-01-2018.
 */

public class ResponseStatus {
    private int statusCode;
    private String message;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

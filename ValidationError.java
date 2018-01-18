package com.dimts.eticketing.Manager.ValidationManager;

/**
 * Created by kartiksharma on 18/01/18.
 */

public class ValidationError {

    private int errorCode;
    private String errorMessage;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMsg) {
        this.errorMessage = errorMsg;
    }
}

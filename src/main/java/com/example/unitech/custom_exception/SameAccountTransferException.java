package com.example.unitech.custom_exception;

import com.example.unitech.utility.exception.CoreException;

public class SameAccountTransferException extends CoreException {
    final String statusCode = "test-9";

    public SameAccountTransferException() {
        super();
    }

    @Override
    public int getStatusCode() {
        return super.getStatusCode();
    }

    @Override
    public String getMessageCode() {
        return statusCode;
    }

    @Override
    public void setStatusCode(int statusCode) {
        super.setStatusCode(statusCode);
    }

    @Override
    public void setMessageCode(String messageCode) {
        super.setMessageCode(statusCode);
    }
}

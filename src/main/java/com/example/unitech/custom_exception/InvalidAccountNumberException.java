package com.example.unitech.custom_exception;

import com.example.unitech.utility.exception.CoreException;

public class InvalidAccountNumberException extends CoreException {
    final String statusCode = "test-6";

    public InvalidAccountNumberException() {
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
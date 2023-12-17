package com.example.unitech.custom_exception;

import com.example.unitech.utility.exception.handler.CoreException;

public class InsufficientBalanceException extends CoreException {
    final String statusCode = "unitech-2008";

    public InsufficientBalanceException() {
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

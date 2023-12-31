package com.example.unitech.custom_exception;

import com.example.unitech.utility.exception.handler.CoreException;

public class InactiveAccountTransferException extends CoreException {
    final String statusCode = "unitech-2007";

    public InactiveAccountTransferException() {
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

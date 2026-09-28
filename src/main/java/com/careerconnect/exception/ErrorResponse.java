package com.careerconnect.exception;

import java.util.Map;

public class ErrorResponse {
    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public ErrorResponse(int status, String message, Map<String, String> errors) {
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    private int status;
    private String message;

    private Map<String ,String> errors;
}

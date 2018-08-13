package com.mounica.pheramor.models;

/**
 * Data model to hold Json response
 */
public class PhermoreResponse {

    private String message;

    private String status;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ClassPojo [message = " + message + ", status = " + status + "]";
    }

    public boolean isStatusOK() {
        return status == "true";
    }
}
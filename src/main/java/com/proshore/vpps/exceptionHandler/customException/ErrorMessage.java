package com.proshore.vpps.exceptionHandler.customException;

import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

public class ErrorMessage {

    private String apiPath;
    private HttpStatus status;
    private List<String> errors = new ArrayList<>();

    public ErrorMessage() {
    }

    public String getApiPath() {
        return apiPath;
    }

    public void setApiPath(String apiPath) {
        this.apiPath = apiPath;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}

package com.company.exceptions;

public class MissingSecondColumnException extends Exception {

    @Override
    public String getMessage() {
        return "matrix doesn't have two columns";
    }
}

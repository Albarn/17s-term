package com.company;

public class NotRealException extends Exception {
    @Override
    public String getMessage() {
        return "matrix element is not real";
    }
}

package com.company;

public class NotRealExeption extends Exception {
    @Override
    public String getMessage() {
        return "matrix element is not real";
    }
}

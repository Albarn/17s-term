package com.company;

public class NonIntegerRangeException extends Exception {

    public String parameterName;

    public NonIntegerRangeException(String parameterName){
        this.parameterName=parameterName;
    }

    @Override
    public String getMessage() {
        return "matrix size "+parameterName+" is not integer";
    }
}

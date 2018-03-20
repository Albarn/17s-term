package com.company;

public class HighRangeException extends Exception {

    public String parameterName;

    public HighRangeException(String parameterName){
        this.parameterName=parameterName;
    }

    @Override
    public String getMessage() {
        return "matrix size "+parameterName+" is too high";
    }
}

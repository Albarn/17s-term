package com.company;

public class NegativeRangeException extends Exception {

    public String parameterName;

    public NegativeRangeException(String parameterName){
        this.parameterName=parameterName;
    }

    @Override
    public String getMessage() {
        return "matrix size "+parameterName+" is negative";
    }
}

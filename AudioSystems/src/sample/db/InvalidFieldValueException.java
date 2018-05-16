package sample.db;

public class InvalidFieldValueException extends Exception {

    private String message;

    public InvalidFieldValueException(String message){
        this.message=message;
    }
    @Override
    public String getMessage() {
        return message;
    }
}

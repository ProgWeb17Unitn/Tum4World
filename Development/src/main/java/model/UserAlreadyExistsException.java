package model;

public class UserAlreadyExistsException extends Exception {
    public UserAlreadyExistsException(){

    }

    public UserAlreadyExistsException(String message){
        super(message);
    }
    public UserAlreadyExistsException(Throwable thr){
        super(thr);
    }
    public UserAlreadyExistsException(String message, Throwable thr){
        super(message, thr);
    }

}

package model;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(){

    }

    public UserNotFoundException(String message){
        super(message);
    }
    public UserNotFoundException(Throwable thr){
        super(thr);
    }
    public UserNotFoundException(String message, Throwable thr){
        super(message, thr);
    }
}

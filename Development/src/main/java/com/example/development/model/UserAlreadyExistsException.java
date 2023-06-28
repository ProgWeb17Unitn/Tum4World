package com.example.development.model;

import java.sql.SQLException;

public class UserAlreadyExistsException extends SQLException {
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

package com.example.development.model;

import java.sql.SQLException;

public class UserNotFoundException extends SQLException {
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

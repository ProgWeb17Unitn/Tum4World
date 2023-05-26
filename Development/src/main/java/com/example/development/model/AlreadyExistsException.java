package com.example.development.model;

import java.sql.SQLException;

public class AlreadyExistsException extends SQLException {
    public AlreadyExistsException(){

    }

    public AlreadyExistsException(String message){
        super(message);
    }
    public AlreadyExistsException(Throwable thr){
        super(thr);
    }
    public AlreadyExistsException(String message, Throwable thr){
        super(message, thr);
    }

}

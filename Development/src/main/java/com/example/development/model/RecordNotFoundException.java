package com.example.development.model;

import java.sql.SQLException;

public class RecordNotFoundException extends SQLException {
    public RecordNotFoundException(){

    }

    public RecordNotFoundException(String message){
        super(message);
    }
    public RecordNotFoundException(Throwable thr){
        super(thr);
    }
    public RecordNotFoundException(String message, Throwable thr){
        super(message, thr);
    }
}

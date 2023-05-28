package com.example.development.model;

public interface DAO<T> {
    void save(T t) throws AlreadyExistsException;

    // void delete(T t);
}

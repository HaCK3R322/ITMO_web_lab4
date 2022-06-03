package com.androsov.itmo_web_lab4.exception;

public class PointNotFoundException extends RuntimeException{
    public PointNotFoundException(long id) {
        super("Point with id " + id + " not found");
    }
}

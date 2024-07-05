package com.myguilds.excepition;

public  class GenectNotFoundException extends RuntimeException {
    public GenectNotFoundException(String message) {
        super(message);
    }
}
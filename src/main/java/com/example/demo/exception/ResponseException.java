package com.example.demo.exception;


public class ResponseException extends Exception {

    public ResponseException(String message) {
        super(message);
    }

    public ResponseException(String message, Throwable cause) {
        super(message, cause);
    }

}


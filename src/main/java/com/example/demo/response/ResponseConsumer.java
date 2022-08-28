package com.example.demo.response;

@FunctionalInterface
public interface ResponseConsumer {
    void accept() throws Exception;
}

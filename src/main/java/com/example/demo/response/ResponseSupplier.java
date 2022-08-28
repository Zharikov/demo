package com.example.demo.response;

@FunctionalInterface
public interface ResponseSupplier<T> {
    T get() throws Exception;
}

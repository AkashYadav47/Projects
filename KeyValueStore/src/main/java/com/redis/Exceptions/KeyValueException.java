package com.redis.Exceptions;

public class KeyValueException extends Exception {
    public KeyValueException(String msg) {
        super(msg);
    }
    public KeyValueException(String msg, Throwable cause) {
        super(msg,cause);
    }
}

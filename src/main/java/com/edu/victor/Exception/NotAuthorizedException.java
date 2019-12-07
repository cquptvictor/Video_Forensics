package com.edu.victor.Exception;

public class NotAuthorizedException extends Exception {
    public NotAuthorizedException(String message) {
        super(message);
    }

    public NotAuthorizedException() {
        super("您没有权限执行此操作");
    }
}

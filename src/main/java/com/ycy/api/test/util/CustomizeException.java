package com.ycy.api.test.util;

/**
 * @author yaocy
 * @date 2024/1/22 19:46
 * @description
 */
public class CustomizeException extends RuntimeException{
    public CustomizeException() {
    }

    public CustomizeException(String message) {
        super(message);
    }
}

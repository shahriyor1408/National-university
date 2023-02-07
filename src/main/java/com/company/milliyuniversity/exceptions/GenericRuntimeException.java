package com.company.milliyuniversity.exceptions;

import lombok.Getter;

@Getter
public class GenericRuntimeException extends RuntimeException {
    protected final Integer statusCode;

    public GenericRuntimeException(String message, Integer statusCode) {
        super(message);
        this.statusCode = statusCode;
    }
}

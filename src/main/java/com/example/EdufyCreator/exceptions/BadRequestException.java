package com.example.EdufyCreator.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//ED-321-AWS
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {
    private final String field;
    private final Object value;

    public BadRequestException(String field, Object value) {
        super("Invalid value for '%s': %s".formatted(field, value));
        this.field = field;
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    public String getField() {
        return field;
    }
}

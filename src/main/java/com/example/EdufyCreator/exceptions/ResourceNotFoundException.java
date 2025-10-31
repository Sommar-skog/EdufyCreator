package com.example.EdufyCreator.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//ED-143-AA
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    private final String resource;
    private final String field;
    private final Object value;

    public ResourceNotFoundException(String resource, String field, Object value) {
        super(String.format("%s not found with %s: %s", resource, field, value));
        this.resource = resource;
        this.field = field;
        this.value = value;
    }

    public String getResource() {
        return resource;
    }

    public String getField() {
        return field;
    }

    public Object getValue() {
        return value;
    }
}

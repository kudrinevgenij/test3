package ru.kudrin.exception;

import lombok.Getter;
import ru.kudrin.validator.Error;

import java.util.List;

public class ValidationException extends RuntimeException {
    @Getter
    private final List<Error> errors;

    public ValidationException(List<Error> errors) {
        this.errors = errors;
    };
}

package ru.kudrin.validator;

public interface Validator<T> {
    ValidationResult isValid(T object);
}

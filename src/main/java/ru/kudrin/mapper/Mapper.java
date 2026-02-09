package ru.kudrin.mapper;

public interface Mapper<T, F> {
    T mapFrom(F f);
}

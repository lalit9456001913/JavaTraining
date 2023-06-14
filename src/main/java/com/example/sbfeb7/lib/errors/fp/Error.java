package com.example.sbfeb7.lib.errors.fp;

import java.util.function.Function;

public record Error<T, E>(E value) implements Result<T, E> {
    @Override
    public <O> Result<O, E> flatMap(Function<T, Result<O, E>> map) {
        return new Error<>(value);
    }

    @Override
    public <O> Result<O, E> map(Function<T, O> map) {
        return new Error<>(value);
    }
}

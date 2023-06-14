package com.example.sbfeb7.lib.errors.fp;

import java.util.function.Function;

public record Ok<T, E>(T value) implements Result<T, E> {
    @Override
    public <O> Result<O, E> flatMap(Function<T, Result<O, E>> map) {
        return map.apply(value);
    }

    @Override
    public <O> Result<O, E> map(Function<T, O> map) {
        return new Ok<>(map.apply(value));
    }
}

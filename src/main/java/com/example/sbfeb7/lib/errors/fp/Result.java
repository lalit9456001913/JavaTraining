package com.example.sbfeb7.lib.errors.fp;

import java.util.function.Function;

public sealed interface Result<T, E> permits Error, Ok {

    <O> Result<O, E> flatMap(Function<T, Result<O, E>> map);

    <O> Result<O, E> map(Function<T, O> map);

}


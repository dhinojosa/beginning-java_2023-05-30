package com.salesforce;

public interface MyFunction<T, R> {
    public R apply(T t);
}

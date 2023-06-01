package com.salesforce;

public interface MyPredicate<T> {
    public boolean test(T item);
}

package com.salesforce;

public interface MyConsumer<T> {
   public void accept(T item);
}

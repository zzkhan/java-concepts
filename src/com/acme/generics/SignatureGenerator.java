package com.acme.generics;

public interface SignatureGenerator<T extends SignatureData> {
    public String generate(T data);
}

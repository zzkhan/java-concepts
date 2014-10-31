package com.acme.generics;

/**
 * Created with IntelliJ IDEA.
 * User: zzkhan
 * Date: 08/05/2014
 * Time: 20:16
 * To change this template use File | Settings | File Templates.
 */
public interface SignatureDataProvider<T extends SignatureData> {
    public T extract();
}

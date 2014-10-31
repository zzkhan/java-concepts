package com.acme.generics;

/**
 * Created with IntelliJ IDEA.
 * User: zzkhan
 * Date: 08/05/2014
 * Time: 20:14
 * To change this template use File | Settings | File Templates.
 */
public class SignatureStrategy {
    SignatureGenerator signatureGenerator = new HMACSignatureGenerator();
    private SignatureDataProvider signatureDataProvider = new HMACSignatureDataProvider();

    public void doStrategy(){
        SignatureData data = signatureDataProvider.extract();
        signatureGenerator.generate(data);
    }
}

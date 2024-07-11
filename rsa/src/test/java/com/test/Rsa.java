package com.example;

import org.junit.Test;

public class Rsa {

    @Test
    public void rsa() throws Exception{
        String filepath="D:/suntech/海关/key/pkcs8.rsa_private_key.pem";


        System.out.println("---------------私钥签名过程------------------");
        String content="0000010000000001~0000010000000010";
        String signstr=RSASignature.sign(content,RSAEncrypt.loadPrivateKeyByFile(filepath));
        System.out.println("sign source: "+content);
        System.out.println("sign:"+signstr);
        System.out.println();

//        System.out.println("---------------公钥校验签名------------------");
//        System.out.println("sign source:"+content);
//        System.out.println("sign:"+signstr);
//
//        System.out.println("result:"+RSASignature.doCheck(content, signstr, RSAEncrypt.loadPublicKeyByFile(filepath)));
//        System.out.println();

    }


}

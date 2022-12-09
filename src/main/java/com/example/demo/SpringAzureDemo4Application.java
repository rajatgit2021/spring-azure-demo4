package com.example.demo;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.util.Arrays;

@SpringBootApplication
@RestController
public class SpringAzureDemo4Application {

    @GetMapping("/message")
    public String message(){
        return "Congratulations! Your first Azure deployment successful";
    }
    //private PublicKey publicKey;
    static {
        Security.addProvider(new BouncyCastleProvider());
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
    }
    @GetMapping("/ekyc")
    public static byte[] generateSessionKey() throws NoSuchAlgorithmException, NoSuchProviderException {
        KeyGenerator kgen = KeyGenerator.getInstance("AES", "BC");
        kgen.init(256);
        SecretKey key = kgen.generateKey();

        return key.getEncoded();
    }
    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchProviderException {
        SpringApplication.run(SpringAzureDemo4Application.class, args);
        System.out.println(Arrays.toString(generateSessionKey()));
    }

}

package com.example.bsep.keystore;

import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.*;
import java.security.cert.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;


@Component
public class KeyStoreWriter {

    public KeyStore keyStore;

    public KeyStoreWriter() {
        try {
            keyStore = KeyStore.getInstance("JKS", "SUN");
        } catch(KeyStoreException | NoSuchProviderException e){
            e.printStackTrace();
        }
    }

    public void loadKeyStore(String fileName, char[] password) {
        try {
            if (fileName != null) {
                keyStore.load(new FileInputStream(fileName), password);
            } else {
                // Ako je cilj kreirati novi KeyStore poziva se i dalje load, pri cemu je prvi parametar null
                keyStore.load(null, password);
            }
        } catch (NoSuchAlgorithmException | CertificateException | IOException e) {
            e.printStackTrace();
        }
    }

    public void saveKeyStore(String fileName, char[] password) {
        try {
            keyStore.store(new FileOutputStream(fileName), password);
        } catch (KeyStoreException | NoSuchAlgorithmException | CertificateException | IOException e) {
            e.printStackTrace();
        }
    }

    public void write(String alias, PrivateKey privateKey, char[] password, Certificate certificate, String issuerAlias) {
        try {
            Certificate root = keyStore.getCertificate("root-ca");
            Certificate inter = keyStore.getCertificate(issuerAlias);
            keyStore.setKeyEntry(alias, privateKey, password, new Certificate[]{root, inter, certificate});
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
    }

    public void deleteEntry(String alias){
        try{
            keyStore.deleteEntry(alias);
        } catch (KeyStoreException e){
            e.printStackTrace();
        }
    }

}

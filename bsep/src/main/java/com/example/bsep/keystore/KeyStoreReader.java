package com.example.bsep.keystore;

import com.example.bsep.data.IssuerData;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.cert.jcajce.JcaX509CertificateHolder;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

@Service
public class KeyStoreReader {

    private KeyStore keyStore;

    public KeyStoreReader(){
        try{
            keyStore = KeyStore.getInstance("JKS", "SUN");
        } catch (KeyStoreException | NoSuchProviderException e){
            e.printStackTrace();
        }
    }

    public IssuerData readIssuerFromStore(String keyStoreFile, String alias, char[] password, char[] keyPass) {
        try {
            // Datoteka se ucitava
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(keyStoreFile));
            keyStore.load(in, password);

            // Iscitava se sertifikat koji ima dati alias
            Certificate cert = keyStore.getCertificate(alias);

            // Iscitava se privatni kljuc vezan za javni kljuc koji se nalazi na sertifikatu sa datim aliasom
            PrivateKey privKey = (PrivateKey) keyStore.getKey(alias, keyPass);

            PublicKey publicKey = keyStore.getCertificate(alias).getPublicKey();

            X500Name issuerName = new JcaX509CertificateHolder((X509Certificate) cert).getSubject();
            return new IssuerData(issuerName, privKey, publicKey);
        } catch (KeyStoreException | NoSuchAlgorithmException | CertificateException |
                UnrecoverableKeyException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Certificate readCertificate(String keyStoreFile, char[] keyStorePass, String alias) {
        try {
            // kreiramo instancu KeyStore
            KeyStore ks = KeyStore.getInstance("JKS", "SUN");

            // ucitavamo podatke
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(keyStoreFile));
            ks.load(in, keyStorePass);

            if (ks.isKeyEntry(alias)) {
                return ks.getCertificate(alias);
            }
        } catch (KeyStoreException | NoSuchProviderException | NoSuchAlgorithmException |
                CertificateException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Certificate[] readCertificateChain(String keyStoreFile, char[] keyStorePass, String alias) {
        try {
            KeyStore ks = KeyStore.getInstance("JKS", "SUN");

            BufferedInputStream in = new BufferedInputStream(new FileInputStream(keyStoreFile));
            ks.load(in, keyStorePass);

            if (ks.isKeyEntry(alias)) {
                return ks.getCertificateChain(alias);
            }
        } catch (KeyStoreException | NoSuchProviderException | NoSuchAlgorithmException |
                CertificateException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public PrivateKey readPrivateKey(String keyStoreFile, char[] keyStorePass, String alias, String pass) { //String keyStorePass
        try {
            // kreiramo instancu KeyStore
            KeyStore ks = KeyStore.getInstance("JKS", "SUN");

            // ucitavamo podatke
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(keyStoreFile));
            ks.load(in, keyStorePass);

            if (ks.isKeyEntry(alias)) {
                return (PrivateKey) ks.getKey(alias, pass.toCharArray());
            }
        } catch (KeyStoreException | NoSuchProviderException | NoSuchAlgorithmException |
                CertificateException | IOException | UnrecoverableKeyException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<String> readSerialNumbers(String keyStoreFile, char[] keyStorePass){
        List<String> sn = new ArrayList<>();
        try {
            KeyStore ks = KeyStore.getInstance("JKS", "SUN");

            BufferedInputStream in = new BufferedInputStream(new FileInputStream(keyStoreFile));
            ks.load(in, keyStorePass);

            Enumeration<String> e = ks.aliases();

            while (e.hasMoreElements()){
                sn.add(e.nextElement());
            }
        } catch(KeyStoreException | NoSuchProviderException | NoSuchAlgorithmException |
                CertificateException | IOException e) {
            e.printStackTrace();
        }
        return sn;
    }
}

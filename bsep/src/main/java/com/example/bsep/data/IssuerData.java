package com.example.bsep.data;

import com.example.bsep.dto.Information;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.X500NameBuilder;
import org.bouncycastle.asn1.x500.style.BCStyle;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

import static com.example.bsep.util.KeyPair.generateKeyPair;

public class IssuerData {

    private X500Name x500Name;

    private PrivateKey privateKey;

    private PublicKey publicKey;

    public IssuerData() {
    }

    public IssuerData(X500Name x500Name, PrivateKey privateKey) {
        this.x500Name = x500Name;
        this.privateKey = privateKey;
    }

    public IssuerData(X500Name x500Name, PrivateKey privateKey, PublicKey publicKey) {
        this.x500Name = x500Name;
        this.privateKey = privateKey;
        this.publicKey = publicKey;
    }

    public IssuerData(Information information, PrivateKey privateKey){
        X500NameBuilder nameBuilder = new X500NameBuilder(BCStyle.INSTANCE);
        nameBuilder.addRDN(BCStyle.CN, information.getCommonName());
        nameBuilder.addRDN(BCStyle.SURNAME, information.getSurname());
        nameBuilder.addRDN(BCStyle.GIVENNAME, information.getGivenName());
        nameBuilder.addRDN(BCStyle.O, information.getOrganizationName());
        nameBuilder.addRDN(BCStyle.OU, information.getOrganizationUnit());
        nameBuilder.addRDN(BCStyle.C, information.getCountry());
        nameBuilder.addRDN(BCStyle.E, information.getEmail());
        nameBuilder.addRDN(BCStyle.UID, information.getUserId());
        this.x500Name = nameBuilder.build();
        this.privateKey = privateKey;
    }

    public X500Name getX500Name() {
        return x500Name;
    }

    public void setX500Name(X500Name x500Name) {
        this.x500Name = x500Name;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(PrivateKey privateKey) {
        this.privateKey = privateKey;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(PublicKey publicKey) {
        this.publicKey = publicKey;
    }
}

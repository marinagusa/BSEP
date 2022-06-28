package com.example.bsep.data;

import com.example.bsep.dto.Information;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.X500NameBuilder;
import org.bouncycastle.asn1.x500.style.BCStyle;

import java.math.BigInteger;
import java.security.PublicKey;
import java.util.Date;
import java.util.Random;

public class SubjectData {

    private PublicKey publicKey;
    private X500Name x500Name;
    private String serialNumber;
    private Date startDate;
    private Date endDate;

    public SubjectData() {
    }

    public SubjectData(PublicKey publicKey, X500Name x500Name, String serialNumber, Date startDate, Date endDate) {
        this.publicKey = publicKey;
        this.x500Name = x500Name;
        this.serialNumber = serialNumber;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public SubjectData(Information information, PublicKey publicKey, Date startDate, Date endDate){
        Random rand = new Random();
        BigInteger sn = new BigInteger(30, rand); // 1 - 1073741824

        X500NameBuilder nameBuilder = new X500NameBuilder(BCStyle.INSTANCE);
        nameBuilder.addRDN(BCStyle.CN, information.getCommonName());
        nameBuilder.addRDN(BCStyle.SURNAME, information.getSurname());
        nameBuilder.addRDN(BCStyle.GIVENNAME, information.getGivenName());
        nameBuilder.addRDN(BCStyle.O, information.getOrganizationName());
        nameBuilder.addRDN(BCStyle.OU, information.getOrganizationUnit());
        nameBuilder.addRDN(BCStyle.C, information.getCountry());
        nameBuilder.addRDN(BCStyle.E, information.getEmail());
        nameBuilder.addRDN(BCStyle.UID, information.getUserId());

        this.publicKey = publicKey;
        this.x500Name = nameBuilder.build();
        this.serialNumber = sn.toString();
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(PublicKey publicKey) {
        this.publicKey = publicKey;
    }

    public X500Name getX500Name() {
        return x500Name;
    }

    public void setX500Name(X500Name x500Name) {
        this.x500Name = x500Name;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "SubjectData{" +
                ", x500Name=" + x500Name +
                ", serialNumber='" + serialNumber + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}

package com.example.bsep.model;

import com.mysql.cj.protocol.ColumnDefinition;

import javax.persistence.*;

@Entity
public class Csr {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String commonName; //CN
    private String organizationUnit; //OU
    private String organizationName; //ON
    private String localityName; //L
    private String stateName; //ST
    private String country; //C
    private String givenName; //GN
    private String surname; //SURNAME
    private String userId; //UID

    private boolean verified;

    @Column(columnDefinition = "TEXT")
    private String csr;

    public Csr() {
    }

    public Csr(String email, String commonName, String organizationUnit, String organizationName, String localityName, String stateName, String country) {
        this.email = email;
        this.commonName = commonName;
        this.organizationUnit = organizationUnit;
        this.organizationName = organizationName;
        this.localityName = localityName;
        this.stateName = stateName;
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getOrganizationUnit() {
        return organizationUnit;
    }

    public void setOrganizationUnit(String organizationUnit) {
        this.organizationUnit = organizationUnit;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getLocalityName() {
        return localityName;
    }

    public void setLocalityName(String localityName) {
        this.localityName = localityName;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCsr() {
        return csr;
    }

    public void setCsr(String csr) {
        this.csr = csr;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    @Override
    public String toString() {
        return "EMAIL=" + email + ",CN=" + commonName + ",OU=" + organizationUnit + ",O="+ organizationName
                + ",L=" + localityName + ",ST=" + stateName + ",C=" + country + ",GIVENNAME=" + givenName + ",UID=" + userId + ",SURNAME=" + surname;
    }
}

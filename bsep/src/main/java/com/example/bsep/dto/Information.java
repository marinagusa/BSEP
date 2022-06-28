package com.example.bsep.dto;

public class Information {

    private String givenName;
    private String surname;
    private String commonName;
    private String organizationName;
    private String organizationUnit;
    private String country;
    private String email;
    private String userId;

    public Information() {
    }

    public Information(String givenName, String surname, String commonName, String organizationName, String organizationUnit, String country, String email, String userId) {
        this.givenName = givenName;
        this.surname = surname;
        this.commonName = commonName;
        this.organizationName = organizationName;
        this.organizationUnit = organizationUnit;
        this.country = country;
        this.email = email;
        this.userId = userId;
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

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getOrganizationUnit() {
        return organizationUnit;
    }

    public void setOrganizationUnit(String organizationUnit) {
        this.organizationUnit = organizationUnit;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}

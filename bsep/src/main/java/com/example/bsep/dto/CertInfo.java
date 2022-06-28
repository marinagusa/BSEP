package com.example.bsep.dto;

import java.security.Principal;

public class CertInfo {

    private String serialNumber;
    private Principal subjectDn;
    private Principal issuerDn;
    private boolean valid;

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Principal getSubjectDn() {
        return subjectDn;
    }

    public void setSubjectDn(Principal subjectDn) {
        this.subjectDn = subjectDn;
    }

    public Principal getIssuerDn() {
        return issuerDn;
    }

    public void setIssuerDn(Principal issuerDn) {
        this.issuerDn = issuerDn;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}

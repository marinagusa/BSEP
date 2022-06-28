package com.example.bsep.dto;

import com.example.bsep.extension.ExtensionCert;

import java.util.Date;
import java.util.List;

public class CertificateDto {

    private String issuerAlias;
    private Information subjectData;
    private Date start;
    private Date end;
    private List<ExtensionCert> extensionList;

    public CertificateDto() {
    }

    public CertificateDto(String issuerAlias, Information subjectData, Date start, Date end, List<ExtensionCert> extensionList) {
        this.issuerAlias = issuerAlias;
        this.subjectData = subjectData;
        this.start = start;
        this.end = end;
        this.extensionList = extensionList;
    }

    public String getIssuerAlias() {
        return issuerAlias;
    }

    public void setIssuerData(String issuerAlias) {
        this.issuerAlias = issuerAlias;
    }

    public Information getSubjectData() {
        return subjectData;
    }

    public void setSubjectData(Information subjectData) {
        this.subjectData = subjectData;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public List<ExtensionCert> getExtensionList() {
        return extensionList;
    }

    public void setExtensionList(List<ExtensionCert> extensionList) {
        this.extensionList = extensionList;
    }
}

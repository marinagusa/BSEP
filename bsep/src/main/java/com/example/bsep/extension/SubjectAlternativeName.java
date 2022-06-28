package com.example.bsep.extension;

import com.example.bsep.data.SubjectData;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.GeneralNames;
import org.bouncycastle.cert.CertIOException;
import org.bouncycastle.cert.X509v3CertificateBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubjectAlternativeName extends ExtensionCert {

    private String dnsName;
    private String ipAddress;
    private String rfc822Name;
    private String uri;
    private String directory;
    private String upn;
    private String registeredId;

    @Override
    public X509v3CertificateBuilder addToBuilder(X509v3CertificateBuilder builder) throws CertIOException{
        List<GeneralName> altNames = new ArrayList<>();
        System.out.println(this);
        if(dnsName != null && isDnsValid()){
            altNames.add(new GeneralName(GeneralName.dNSName, dnsName));
        }
        if(ipAddress != null && isIpAddressValid()){
            altNames.add(new GeneralName(GeneralName.iPAddress, ipAddress));
        }
        if(rfc822Name != null && isRfcNameValid()){
            altNames.add(new GeneralName(GeneralName.rfc822Name, rfc822Name));
        }
        if(uri != null && isuriValid()){
            altNames.add(new GeneralName(GeneralName.uniformResourceIdentifier, uri));
        }
        if(directory != null && isDirectoryValid()){
            altNames.add(new GeneralName(GeneralName.directoryName, directory));
        }
        if(upn != null && isUpnValid()){
            altNames.add(new GeneralName(GeneralName.ediPartyName, upn));
        }
        if(registeredId != null && isRegisteredIdValid()){
            altNames.add(new GeneralName(GeneralName.registeredID, registeredId));
        }
        System.out.println("SUBJECT ALTERNATIVE NAME");
        GeneralName[] arr = altNames.toArray(new GeneralName[] {});
        System.out.println(Arrays.toString(arr));

        GeneralNames subjectAltNames = GeneralNames.getInstance(new DERSequence(arr));

        builder.addExtension(Extension.subjectAlternativeName, isCritical(), subjectAltNames);
        return builder;
    }

    private boolean isDnsValid(){
        String dnsPattern = "^((?!-)[A-Za-z0-9-]{1,63}(?<!-)\\\\.)+[A-Za-z]{2,6}$";
        return this.dnsName.matches(dnsPattern);
    }

    private boolean isIpAddressValid(){
        String IPV4_PATTERN_SHORTEST =
                "^((25[0-5]|(2[0-4]|1[0-9]|[1-9]|)[0-9])(\\.(?!$)|$)){4}$";
        return this.ipAddress.matches(IPV4_PATTERN_SHORTEST);
    }

    private boolean isRfcNameValid(){
        //eg: test_ad.fade@mail.com
        String rfc822Regex = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
        return this.rfc822Name.matches(rfc822Regex);
    }

    private boolean isuriValid(){
        String uriRegex = "(https?://|www\\.)[-a-zA-Z0-9+&@#/%?=~_|!:.;]*[-a-zA-Z0-9+&@#/%=~_|]";
        return this.uri.matches(uriRegex);
    }


    private boolean isRegisteredIdValid() {
        return this.registeredId.length() > 4;
    }

    private boolean isUpnValid() {
        return this.upn.length() > 5;
    }

    private boolean isDirectoryValid() {
        return false;
    }

    public String getdnsName() {
        return dnsName;
    }

    public void setdnsName(String dnsName) {
        this.dnsName = dnsName;
    }

    public String getipAddress() {
        return ipAddress;
    }

    public void setipAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getrfc822Name() {
        return rfc822Name;
    }

    public void setrfc822Name(String rfc822Name) {
        this.rfc822Name = rfc822Name;
    }

    public String geturi() {
        return uri;
    }

    public void seturi(String uri) {
        this.uri = uri;
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public String getUPN() {
        return upn;
    }

    public void setupn(String upn) {
        this.upn = upn;
    }

    public String getRegisteredId() {
        return registeredId;
    }

    public void setRegisteredId(String registeredId) {
        this.registeredId = registeredId;
    }

    @Override
    public String toString() {
        return "SubjectAlternativeName{" +
                "dnsName='" + dnsName + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", rfc822Name='" + rfc822Name + '\'' +
                ", uri='" + uri + '\'' +
                ", directory='" + directory + '\'' +
                ", upn='" + upn + '\'' +
                ", registeredId='" + registeredId + '\'' +
                '}';
    }
}

package com.example.bsep.extension;

import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.KeyUsage;
import org.bouncycastle.cert.CertIOException;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import java.util.List;

public class KeyUsageExt extends ExtensionCert {

    private List<String> usages;

    @Override
    public X509v3CertificateBuilder addToBuilder(X509v3CertificateBuilder builder) throws CertIOException {
        if(usages == null || usages.size() == 0)
            return builder;

        int keyUsageFlag = KeyUsage.keyAgreement;
        if(usages.contains("Certificate signing"))
            keyUsageFlag |= KeyUsage.keyCertSign;
        if(usages.contains("Decipher only"))
            keyUsageFlag |= KeyUsage.decipherOnly;
        if(usages.contains("Key agreement"))
            keyUsageFlag |= KeyUsage.keyAgreement;
        if(usages.contains("CRL sign"))
            keyUsageFlag |= KeyUsage.cRLSign;
        if(usages.contains("Digital signature"))
            keyUsageFlag |= KeyUsage.digitalSignature;
        if(usages.contains("Key encipherment"))
            keyUsageFlag |= KeyUsage.keyEncipherment;
        if(usages.contains("Data encipherment"))
            keyUsageFlag |= KeyUsage.dataEncipherment;
        if(usages.contains("Encipher only"))
            keyUsageFlag |= KeyUsage.encipherOnly;
        if(usages.contains("Non repudiation"))
            keyUsageFlag |= KeyUsage.nonRepudiation;

        org.bouncycastle.asn1.x509.KeyUsage ku = new org.bouncycastle.asn1.x509.KeyUsage(keyUsageFlag);
        builder.addExtension(Extension.keyUsage, isCritical(), ku);
        return builder;
    }

    public List<String> getUsages() {
        return usages;
    }

    public void setUsages(List<String> usages) {
        this.usages = usages;
    }
}

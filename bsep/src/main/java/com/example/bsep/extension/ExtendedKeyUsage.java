package com.example.bsep.extension;

import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.KeyPurposeId;
import org.bouncycastle.cert.CertIOException;
import org.bouncycastle.cert.X509v3CertificateBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ExtendedKeyUsage extends ExtensionCert {

    private List<String> usages;

    @Override
    public X509v3CertificateBuilder addToBuilder(X509v3CertificateBuilder builder) throws CertIOException {
        KeyPurposeId[] kpiArr = new KeyPurposeId[10];

        int position = 0;
        for (String usage: usages) {
            switch (usage) {
                case "Any Eku":
                    kpiArr[position] = KeyPurposeId.anyExtendedKeyUsage;
                    position++;
                    break;
                case "Code signing":
                    kpiArr[position] = KeyPurposeId.id_kp_codeSigning;
                    position++;
                    break;
                case "E-mail protection":
                    kpiArr[position] = KeyPurposeId.id_kp_emailProtection;
                    position++;
                    break;
                case "IP es":
                    kpiArr[position] = KeyPurposeId.id_kp_ipsecEndSystem;
                    position++;
                    break;
                case "IP su":
                    kpiArr[position] = KeyPurposeId.id_kp_ipsecUser;
                    position++;
                    break;
                case "OCSP":
                    kpiArr[position] = KeyPurposeId.id_kp_OCSPSigning;
                    position++;
                    break;
                case "TLS wca":
                    kpiArr[position] = KeyPurposeId.id_kp_clientAuth;
                    position++;
                    break;
                case "TLS wsa":
                    kpiArr[position] = KeyPurposeId.id_kp_serverAuth;
                    position++;
                    break;
                case "Time stamping":
                    kpiArr[position] = KeyPurposeId.id_kp_timeStamping;
                    position++;
                    break;
                case "IPTT":
                    kpiArr[position] = KeyPurposeId.id_kp_ipsecTunnel;
                    position++;
                    break;
            }
        }
        System.out.println(position);
        System.out.println(kpiArr.length);
        for(int i = 0; i < kpiArr.length; i++){
            System.out.println(kpiArr[i]);
        }
        KeyPurposeId[] withoutNull = Arrays.stream(kpiArr).limit(position).collect(Collectors.toList()).toArray(new KeyPurposeId[position]);

        builder.addExtension(Extension.extendedKeyUsage, isCritical(), new org.bouncycastle.asn1.x509.ExtendedKeyUsage(withoutNull));
        return builder;
    }

    public List<String> getUsages() {
        return usages;
    }

    public void setUsages(List<String> usages) {
        this.usages = usages;
    }
}

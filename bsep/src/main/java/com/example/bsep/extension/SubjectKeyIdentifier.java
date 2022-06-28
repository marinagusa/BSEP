package com.example.bsep.extension;

import com.example.bsep.data.SubjectData;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.cert.CertIOException;
import org.bouncycastle.cert.X509v3CertificateBuilder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SubjectKeyIdentifier extends ExtensionCert {

    public X509v3CertificateBuilder addToBuilderSKI(X509v3CertificateBuilder builder, SubjectData sd) throws CertIOException, NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        byte[] hash = md.digest(sd.getPublicKey().getEncoded());
        builder.addExtension(Extension.subjectKeyIdentifier, isCritical(), new org.bouncycastle.asn1.x509.SubjectKeyIdentifier(hash));
        return builder;
    }
}

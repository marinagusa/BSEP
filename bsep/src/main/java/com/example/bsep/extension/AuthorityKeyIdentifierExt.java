package com.example.bsep.extension;

import org.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.AuthorityKeyIdentifier;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.cert.CertIOException;
import org.bouncycastle.cert.X509ExtensionUtils;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.bc.BcDigestCalculatorProvider;
import org.bouncycastle.x509.extension.AuthorityKeyIdentifierStructure;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;

public class AuthorityKeyIdentifierExt extends ExtensionCert {

    private BigInteger acsn;

    public X509v3CertificateBuilder addToBuilderAki(X509v3CertificateBuilder builder, X509Certificate issuerCert) throws CertIOException, OperatorCreationException, CertificateParsingException {
        builder.addExtension(Extension.authorityKeyIdentifier, isCritical(), new AuthorityKeyIdentifierStructure(issuerCert));
        return builder;
    }

    public BigInteger getAcsn() {
        return acsn;
    }

    public void setAcsn(BigInteger acsn) {
        this.acsn = acsn;
    }
}

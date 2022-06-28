package com.example.bsep.extension;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.bouncycastle.cert.CertIOException;
import org.bouncycastle.cert.X509v3CertificateBuilder;

import java.security.NoSuchAlgorithmException;


@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = AuthorityKeyIdentifierExt.class, name = "aki"),
        @JsonSubTypes.Type(value = ExtendedKeyUsage.class, name = "eku"),
        @JsonSubTypes.Type(value = KeyUsageExt.class, name = "ku"),
        @JsonSubTypes.Type(value = SubjectAlternativeName.class, name = "san"),
        @JsonSubTypes.Type(value = SubjectKeyIdentifier.class, name = "ski")
})
public class ExtensionCert {

    protected boolean critical;

    public X509v3CertificateBuilder addToBuilder(X509v3CertificateBuilder builder) throws CertIOException, NoSuchAlgorithmException {
        return null;
    }

    public boolean isCritical() {
        return critical;
    }

    public void setCritical(boolean critical) {
        this.critical = critical;
    }
}

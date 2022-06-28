package com.example.bsep.service;

import com.example.bsep.data.IssuerData;
import com.example.bsep.data.SubjectData;
import com.example.bsep.extension.AuthorityKeyIdentifierExt;
import com.example.bsep.extension.ExtensionCert;
import com.example.bsep.extension.SubjectKeyIdentifier;
import com.example.bsep.keystore.KeyStoreReader;
import com.example.bsep.keystore.KeyStoreWriter;
import com.example.bsep.repository.CsrRepository;
import com.example.bsep.util.Constants;
import org.bouncycastle.asn1.x509.AuthorityKeyIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.cert.CertIOException;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigInteger;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;


@Service
public class CertificateGeneratorService {

    @Autowired
    private KeyStoreWriter keyStoreWriter;

    @Autowired
    private KeyStoreReader keyStoreReader;

    @Autowired
    private CsrRepository csrRepository;

    @Autowired
    private MailingService mailingService;

    public X509Certificate generateCertificate(SubjectData subjectData, IssuerData issuerData, List<ExtensionCert> extensionList, String issuerAlias)  {
        try {
            // Posto klasa za generisanje sertifiakta ne moze da primi direktno privatni kljuc pravi se builder za objekat
            // Ovaj objekat sadrzi privatni kljuc izdavaoca sertifikata i koristiti se za potpisivanje sertifikata
            // Parametar koji se prosledjuje je algoritam koji se koristi za potpisivanje sertifiakta
            JcaContentSignerBuilder builder = new JcaContentSignerBuilder("SHA256WithRSAEncryption");

            // Takodje se navodi koji provider se koristi, u ovom slucaju Bouncy Castle
            builder = builder.setProvider("BC");

            // Formira se objekat koji ce sadrzati privatni kljuc i koji ce se koristiti za potpisivanje sertifikata
            ContentSigner contentSigner = builder.build(issuerData.getPrivateKey());

            // Postavljaju se podaci za generisanje sertifiakta
            X509v3CertificateBuilder certGen = new JcaX509v3CertificateBuilder(issuerData.getX500Name(),
                    new BigInteger(subjectData.getSerialNumber()),
                    subjectData.getStartDate(),
                    subjectData.getEndDate(),
                    subjectData.getX500Name(),
                    subjectData.getPublicKey());

            // Dodavanje ekstenzija u sertifikat
            if(extensionList != null) {
                for (ExtensionCert extension : extensionList) {
                    if (extension instanceof SubjectKeyIdentifier) {
                        SubjectKeyIdentifier ski = (SubjectKeyIdentifier) extension;
                        certGen = ski.addToBuilderSKI(certGen, subjectData);
                        System.out.println(certGen);
                    }
                    else if(extension instanceof AuthorityKeyIdentifierExt){
                        AuthorityKeyIdentifierExt aki = (AuthorityKeyIdentifierExt) extension;
                        X509Certificate issuerCert = (X509Certificate) keyStoreReader.readCertificate(Constants.PATH, Constants.PASSWORD, "inter");
                        certGen = aki.addToBuilderAki(certGen, issuerCert);
                    }
                    else {
                        certGen = extension.addToBuilder(certGen);
                    }
                }
            }
            // Generise se sertifikat
            X509CertificateHolder certHolder = certGen.build(contentSigner);

            // Builder generise sertifikat kao objekat klase X509CertificateHolder
            // Nakon toga je potrebno certHolder konvertovati u sertifikat, za sta se koristi certConverter
            JcaX509CertificateConverter certConverter = new JcaX509CertificateConverter();
            certConverter = certConverter.setProvider("BC");

            // Konvertuje objekat u sertifikat
            X509Certificate certificate = certConverter.getCertificate(certHolder);
            certificate.verify(issuerData.getPublicKey(), "BC");

            // Cuvanje sertifikata u keystore
            keyStoreWriter.loadKeyStore(Constants.PATH, Constants.PASSWORD);
            keyStoreWriter.write(subjectData.getSerialNumber(),
                                issuerData.getPrivateKey(),
                                Constants.PASSWORD,
                                certificate, issuerAlias);
            keyStoreWriter.saveKeyStore(Constants.PATH, Constants.PASSWORD);

            return certificate;
        } catch (IllegalArgumentException | IllegalStateException | OperatorCreationException | CertificateException | NoSuchAlgorithmException | InvalidKeyException | NoSuchProviderException | SignatureException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

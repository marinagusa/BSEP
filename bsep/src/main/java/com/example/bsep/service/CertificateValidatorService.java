package com.example.bsep.service;

import com.example.bsep.model.RevokedCertificate;
import com.example.bsep.repository.RevokedCertificateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Date;

@Service
public class CertificateValidatorService {

    @Autowired
    private RevokedCertificateRepository revokedCertificateRepository;

    public boolean isCertificateValid(X509Certificate issuer, X509Certificate certificate) throws CertificateException, NoSuchAlgorithmException, SignatureException, InvalidKeyException, NoSuchProviderException {
        RevokedCertificate r = revokedCertificateRepository.findByAlias(certificate.getSerialNumber().toString());
        if(r != null){
            return false;
        }
        certificate.verify(issuer.getPublicKey());
        Date validUntil = certificate.getNotAfter();
        Date validFrom = certificate.getNotBefore();
        Date today = new Date();
        if(today.before(validFrom) || today.after(validUntil))
            return false;
        return true;
    }
}

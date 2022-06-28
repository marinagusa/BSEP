package com.example.bsep.service;

import com.example.bsep.keystore.KeyStoreReader;
import com.example.bsep.keystore.KeyStoreWriter;
import com.example.bsep.model.RevokedCertificate;
import com.example.bsep.repository.RevokedCertificateRepository;
import com.example.bsep.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.cert.Certificate;
import java.util.NoSuchElementException;
import java.util.List;

@Service
public class CertificateRevokerService {

    @Autowired
    private KeyStoreWriter keyStoreWriter;

    @Autowired
    private KeyStoreReader keyStoreReader;

    @Autowired
    private RevokedCertificateRepository repository;

    public RevokedCertificate revokeCertificate(String serialNumber, String reason){
        Certificate certificate = keyStoreReader.readCertificate(Constants.PATH, Constants.PASSWORD, serialNumber);
        if(certificate == null){
            throw new NoSuchElementException();
        }

        keyStoreWriter.loadKeyStore(Constants.PATH, Constants.PASSWORD);
        keyStoreWriter.deleteEntry(serialNumber);
        keyStoreWriter.saveKeyStore(Constants.PATH, Constants.PASSWORD);

        RevokedCertificate rc = new RevokedCertificate(serialNumber, reason);

        //notify user that certificate is revoked?
        return repository.save(rc);
    }

    public List<RevokedCertificate> findAll(){
        return repository.findAll();
    }

}

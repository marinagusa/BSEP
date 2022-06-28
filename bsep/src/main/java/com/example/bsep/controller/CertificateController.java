package com.example.bsep.controller;


import com.example.bsep.data.IssuerData;
import com.example.bsep.data.SubjectData;
import com.example.bsep.dto.CertInfo;
import com.example.bsep.dto.CertificateDto;
import com.example.bsep.keystore.KeyStoreReader;
import com.example.bsep.model.RevokedCertificate;
import com.example.bsep.service.CertificateGeneratorService;
import com.example.bsep.service.CertificateRevokerService;
import com.example.bsep.service.CertificateValidatorService;
import com.example.bsep.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.cert.Certificate;
import java.util.List;

//TO DO : ... WITH CERTIFICATE CHAINING !!!!
@RestController
@RequestMapping(value = "certificates")
public class CertificateController {

    @Autowired
    private CertificateGeneratorService service;

    @Autowired
    private CertificateRevokerService revokerService;

    @Autowired
    private CertificateValidatorService validatorService;

    @Autowired
    private KeyStoreReader keyStoreReader;

    @GetMapping("/all")
    public ResponseEntity<List<String>> findAll(){
        return new ResponseEntity<>(keyStoreReader.readSerialNumbers(Constants.PATH, Constants.PASSWORD), HttpStatus.OK);
    }

    @GetMapping("/one/{alias}")
    public ResponseEntity<CertInfo> findOne(@PathVariable String alias){
        Certificate[] chain = keyStoreReader.readCertificateChain(Constants.PATH, Constants.PASSWORD, alias);
        X509Certificate c = (X509Certificate) chain[chain.length - 1]; //last in chain
        CertInfo dto = new CertInfo();
        dto.setIssuerDn(c.getIssuerX500Principal());
        dto.setSerialNumber(alias);
        dto.setSubjectDn(c.getSubjectX500Principal());
        try {
            dto.setValid(validatorService.isCertificateValid((X509Certificate) chain[chain.length - 2], c));
        } catch (CertificateException | NoSuchAlgorithmException | SignatureException | InvalidKeyException | NoSuchProviderException | IndexOutOfBoundsException e) {
            e.printStackTrace();
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping(value = "/")
    @PreAuthorize("hasAuthority('ADD_CERTIFICATE')")
    public ResponseEntity<X509Certificate> add(@RequestBody CertificateDto certificateDto) {
        IssuerData id = keyStoreReader.readIssuerFromStore(Constants.PATH, certificateDto.getIssuerAlias(), Constants.PASSWORD, Constants.PASSWORD);
        KeyPair keyPair = com.example.bsep.util.KeyPair.generateKeyPair();
        if(keyPair == null){
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        }
        SubjectData sd =  new SubjectData(certificateDto.getSubjectData(), keyPair.getPublic(), certificateDto.getStart(), certificateDto.getEnd());
        X509Certificate cert = service.generateCertificate(sd, id,
                                certificateDto.getExtensionList(), certificateDto.getIssuerAlias());
        System.out.println(sd);
        System.out.println(cert);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value="/revoke/{serialNumber}/{reason}")
    @PreAuthorize("hasAuthority('REVOKE_CERTIFICATE')")
    public ResponseEntity<RevokedCertificate> revoke(@PathVariable String serialNumber, @PathVariable String reason){
        return new ResponseEntity<>(revokerService.revokeCertificate(serialNumber, reason), HttpStatus.OK);
    }

    @GetMapping(value="/get-revoked")
    public ResponseEntity<List<RevokedCertificate>> getRevoked(){
        return new ResponseEntity<>(revokerService.findAll(), HttpStatus.OK);
    }

}

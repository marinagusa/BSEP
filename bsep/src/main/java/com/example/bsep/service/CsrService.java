package com.example.bsep.service;

import com.example.bsep.model.Csr;
import com.example.bsep.repository.CsrRepository;
import org.bouncycastle.openssl.PEMWriter;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;
import org.bouncycastle.pkcs.PKCS10CertificationRequestBuilder;
import org.bouncycastle.pkcs.jcajce.JcaPKCS10CertificationRequestBuilder;
import org.bouncycastle.util.io.pem.PemObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.x500.X500Principal;
import java.io.StringWriter;
import java.security.*;

import static com.example.bsep.util.KeyPair.generateKeyPair;
import java.util.List;

@Service
public class CsrService {

    @Autowired
    private CsrRepository repository;

    @Autowired
    private MailingService mailingService;

    public Csr add(Csr csr) throws Exception{
        KeyPair pair = generateKeyPair();
        PKCS10CertificationRequestBuilder p10Builder = new JcaPKCS10CertificationRequestBuilder(new X500Principal(csr.toString()), pair.getPublic());
        JcaContentSignerBuilder csBuilder = new JcaContentSignerBuilder("SHA256withRSA");
        ContentSigner signer = csBuilder.build(pair.getPrivate());
        PKCS10CertificationRequest csrS = p10Builder.build(signer);

        PemObject pemObject = new PemObject("CERTIFICATE REQUEST", csrS.getEncoded());
        StringWriter str = new StringWriter();
        PEMWriter pemWriter = new PEMWriter(str);
        pemWriter.writeObject(pemObject);
        pemWriter.close();
        str.close();

        csr.setCsr(str.toString());

        Csr saved = repository.save(csr);
        mailingService.sendCsrConfirmationEmail(saved);

        return saved;
    }

    public boolean verify(Long id){
        Csr found = repository.findById(id).orElseThrow();
        found.setVerified(true);
        return true;
    }

    public List<Csr> findAll(){
        return repository.findAll();
    }

    public boolean delete(Long id){
        try{
            repository.deleteById(id);
            return true;
        } catch(Exception e){
            return false;
        }
    }

    public Csr findById(Long id){
        return repository.findById(id).orElseThrow();
    }

}

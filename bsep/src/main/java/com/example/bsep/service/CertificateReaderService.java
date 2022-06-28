package com.example.bsep.service;

import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.Collection;
import java.util.Iterator;

@Service
public class CertificateReaderService {

    private void readFromBase64EncFile(String path) {
        try {
            FileInputStream fis = new FileInputStream(path);
            BufferedInputStream bis = new BufferedInputStream(fis);

            CertificateFactory cf = CertificateFactory.getInstance("X.509");

            // Cita sertifikat po sertifikat
            // Svaki certifikat je izmedju
            //-----BEGIN CERTIFICATE-----,
            // i
            //-----END CERTIFICATE-----.
            while (bis.available() > 0) {
                Certificate cert = cf.generateCertificate(bis);
                System.out.println(cert.toString());
            }
        } catch (CertificateException | IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("rawtypes")
    private void readFromBinEncFile(String path) {
        try {
            FileInputStream fis = new FileInputStream(path);
            CertificateFactory cf = CertificateFactory.getInstance("X.509");

            // Ovde se vade svi sertifkati
            Collection c = cf.generateCertificates(fis);
            for (Object o : c) {
                Certificate cert = (Certificate) o;
                System.out.println(cert);
            }
        } catch (FileNotFoundException | CertificateException e) {
            e.printStackTrace();
        }

    }
}

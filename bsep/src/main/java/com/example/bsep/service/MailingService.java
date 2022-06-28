package com.example.bsep.service;

import com.example.bsep.model.Csr;
import com.example.bsep.model.RevokedCertificate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class MailingService {
    //TO DO : when accepting CSR and when REVOKING certificate

    public JavaMailSender getJavaMailSender(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername("marinaseqrity77711@gmail.com");
        mailSender.setPassword("nqepiemtkkpxtkwe"); //875866 //141041 //win-nqepiemtkkpxtkwe //mac-kuguiwcebhrczzhu

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }

    public void sendCsrConfirmationEmail(Csr csr){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("marinaseqrity77711@gmail.com");
        message.setTo(csr.getEmail());
        message.setSubject("CSR confirmation");
        message.setText("Dear " + csr.getCommonName() + "\n\n Your certificate request is being processed.\n" +
                "Click on link below to confirm given information.\n" +
                "https://localhost:3000/csr-confirm/" + csr.getId() +
                "\n\nYou will be notified once request is handled.\n\nSincerely, \nSecurity team.");
        getJavaMailSender().send(message);
    }

    public void sendRevokeEmail(RevokedCertificate rc){

    }

}

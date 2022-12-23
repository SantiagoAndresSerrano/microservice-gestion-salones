package com.springboot.app.gestionsalones.servicesImpl;


import com.springboot.app.gestionsalones.services.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
public class EmailSenderServiceImp{

    @Autowired
    private EmailSenderService emailSenderService;

    @Value("{spring.mail.username}")
    private String emailFrom;

    public void enviarEmail(String asunto, String mensaje, String destinatario) throws MessagingException {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(emailFrom);
        simpleMailMessage.setText(mensaje);
        simpleMailMessage.setSubject(asunto);
        simpleMailMessage.setTo(destinatario);

        emailSenderService.sendEmail(simpleMailMessage);
    }

}
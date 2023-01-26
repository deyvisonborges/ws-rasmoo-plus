package com.cliente.rasmoo.plus.integrations.impl;

import com.cliente.rasmoo.plus.integrations.MailIntegration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class MailIntegrationImpl implements MailIntegration {

    @Autowired
    private JavaMailSender javaMailSender;
    @Override
    public void send(String mailTo, String message) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(mailTo);
        simpleMailMessage.setSubject("Acesso liberado");
        simpleMailMessage.setText("Login: "+mailTo+"Senha: aluno123");
        javaMailSender.send(simpleMailMessage);
    }
}

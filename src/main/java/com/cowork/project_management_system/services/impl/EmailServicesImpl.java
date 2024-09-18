package com.cowork.project_management_system.services.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServicesImpl {
private final JavaMailSender javaMailSender;
    public void sendEmailWithToken(String userEmail,String link) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper=new MimeMessageHelper(mimeMessage,"utf-8");
        String subject = "join project Team Invitation";
        String text = "Click the link to join the project team "+link;
        helper.setSubject(subject);
        helper.setText(text,true);
        helper.setTo(userEmail);
        try{
            javaMailSender.send(mimeMessage);
        }catch (MailException mailException){
            throw new MailSendException("failed to send email");
        }
}

}

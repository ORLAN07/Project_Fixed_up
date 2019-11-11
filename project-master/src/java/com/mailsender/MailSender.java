/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mailsender;

import java.io.Serializable;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.NewsAddress;

/**
 *
 * @author ORLANDO
 */
public class MailSender implements Serializable {

    public final static String HOST_EMAIL_GMAIL = "smtp.gmail.com";

    public void sendMail(String fromMail, String username, String password,
            String toMail, String subject, String message) throws AddressException, MessagingException {

        Properties props = System.getProperties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.fallback", "false");
        props.put("mail.smtp.ssl.trust", HOST_EMAIL_GMAIL);

        Session mailSession = Session.getDefaultInstance(props, null);
        mailSession.setDebug(true);

        Message mailMessage = new MimeMessage(mailSession);

        mailMessage.setFrom(new InternetAddress("correofixedup@gmail.com"));
        mailMessage.setRecipient(Message.RecipientType.CC, new NewsAddress(toMail));
        mailMessage.setContent(message, "text/html");
        mailMessage.setSubject(subject);

        Transport transport = mailSession.getTransport("smtp");
        transport.connect("smtp.gmail.com", "correofixedup@gmail.com", "fixedupsena");
        transport.sendMessage(mailMessage, mailMessage.getAllRecipients());

    }
    
    public void enviarEmailCliente(String toMail, String subject, String message) throws AddressException, MessagingException {

        Properties props = System.getProperties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.fallback", "false");
        props.put("mail.smtp.ssl.trust", HOST_EMAIL_GMAIL);

        Session mailSession = Session.getDefaultInstance(props, null);
        mailSession.setDebug(true);

        Message mailMessage = new MimeMessage(mailSession);

        mailMessage.setFrom(new InternetAddress("correofixedup@gmail.com"));
        mailMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(toMail));
        mailMessage.setSubject(subject);
        mailMessage.setContent(message, "text/html");

        Transport transport = mailSession.getTransport("smtp");
        transport.connect("smtp.gmail.com", "correofixedup@gmail.com", "fixedupsena");
        transport.sendMessage(mailMessage, mailMessage.getAllRecipients());

    }

}

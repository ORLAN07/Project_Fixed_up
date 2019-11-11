/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.mailsender.MailSender;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author ORLANDO
 */
@ManagedBean(name = "MainController")
@SessionScoped
public class MainController implements Serializable {

    private String fromMail;
    private String username;
    private String password;
    private String toMail;
    private String subject;
    private String message;

    public String getFromMail() {
        return fromMail;
    }

    public void setFromMail(String fromMail) {
        this.fromMail = fromMail;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToMail() {
        return toMail;
    }

    public void setToMail(String toMail) {
        this.toMail = toMail;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void send() {
        try {
            MailSender mailSender = new MailSender();
            mailSender.sendMail("correofixedup@gmail.com", "correofixedup@gmail.com", "fixedupsena", "correofixedup@gmail.com", subject, message);
            FacesContext context = FacesContext.getCurrentInstance();

            
            context.addMessage("username", new FacesMessage("Tu mensaje se ha enviado", "Nos contactaremos contigo"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

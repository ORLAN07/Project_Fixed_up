package com.recuperarcontraseña;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.mailsender.MailSender;
import static com.mailsender.MailSender.HOST_EMAIL_GMAIL;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import org.dao.PersonaFacadeLocal;
import org.entidades.Persona;

/**
 *
 * @author ORLANDO
 */
@Named(value = "emailrecuperarcontraseña")
@SessionScoped
public class emailrecuperarcontraseña implements Serializable {

    @EJB
    private PersonaFacadeLocal pfl;
    private Persona persona;
    private String emailRemitente;
    private String passRemitente;
    private String emailDestinatario;
    private String Contrasena;
    private Session session;
    private MimeMessage mimeMessage;

    public emailrecuperarcontraseña() {
    }

    public String getEmailDestinatario() {
        return emailDestinatario;
    }

    public void setEmailDestinatario(String emailDestinatario) {
        this.emailDestinatario = emailDestinatario;
    }

    public emailrecuperarcontraseña(String emailRemitente, String passRemitente, String emailDestinatario) {
        this.emailRemitente = emailRemitente;
        this.passRemitente = passRemitente;
        this.emailDestinatario = emailDestinatario;
    }

    private void init() {
        try {
            Properties propiedades = new Properties();

            propiedades.setProperty("mail.smtp.host", "smtp.gmail.com");
            propiedades.setProperty("mail.smtp.starttls.enable", "true");
            propiedades.setProperty("mail.smtp.port", "25");//587-25
            propiedades.setProperty("mail.smtp.user", this.emailRemitente);
            propiedades.setProperty("mail.smtp.auth", "true");
            propiedades.setProperty("mail.smtp.ssl.trust", HOST_EMAIL_GMAIL);
            session = Session.getDefaultInstance(propiedades);
            session.setDebug(true);
            mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(emailRemitente));
            mimeMessage.setRecipients(Message.RecipientType.TO, emailDestinatario);

        } catch (MessagingException ex) {
            Logger.getLogger(emailrecuperarcontraseña.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    public void enviarSimple(String asunto, String contenido) {
        try {
            if (asunto != null && contenido != null) {

                init();

                Multipart contenidoMensaje = new MimeMultipart();
                BodyPart text = new MimeBodyPart();
                text.setText(contenido);
                contenidoMensaje.addBodyPart(text);

                mimeMessage.setSubject(asunto);
                mimeMessage.setContent(contenidoMensaje, "text/html");

                Transport transport = session.getTransport("smtp");
                transport.connect(emailRemitente, passRemitente);
                transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
                transport.close();
                emailDestinatario = "";
            } else {
                System.out.println("Falso");
            }

        } catch (MessagingException ex) {
            Logger.getLogger(emailrecuperarcontraseña.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
     
    public void asignacionCorreos() {
        System.out.println("Ingresamos a enviar correo" + emailDestinatario);
        FacesContext fc = FacesContext.getCurrentInstance();
        int year = Calendar.getInstance().get(Calendar.YEAR);

        try {
            if (emailDestinatario != null && !emailDestinatario.equals("")) {
                persona = pfl.recuperacontrasena(emailDestinatario);
                if (persona != null) {
                    FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha enviado la contraseña a su correo, ", "Por favor revise su bandeja de entrada");
                    fc.addMessage("correoLost", fm);
                    Contrasena = persona.getPassword();
                    emailrecuperarcontraseña email = new emailrecuperarcontraseña("correofixedup@gmail.com", "fixedupsena", emailDestinatario);
                    email.enviarSimple("Restablecimiento contraseña fixedUp "
                            + "contraseña Fixed up", "Tu contraseña es: " + Contrasena + " "
                    + "Este correo es de carácter informativo, por favor no responder "
                        + " Fixedup " + year + " ");

                } else {
                    System.out.println("Email no esta en base de datos");
                    FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, "Email no esta en base de datos", "Por favor contacte con el administrador");
                    fc.addMessage("correoLost", fm);

                }
            } else {
                System.out.println("Email es nulo o vacio");
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, "Todos los campos son obligatorios", "Diligencie todos los campos");
                fc.addMessage("correoLost", fm);

            }
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

}

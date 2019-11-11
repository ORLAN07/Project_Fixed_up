/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lang;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author David
 */
@Named(value = "idioma")
@SessionScoped
public class Idioma implements Serializable {

    private Locale languageSelected;

    public Idioma() {

    }

    @PostConstruct
    public void init() {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        Locale idiomaUsuario = ec.getRequestLocale();
        boolean support = false;
        for (Locale l : getSupportLanguages()) {
            if (l.getLanguage().equals(idiomaUsuario.getLanguage())) {
                support = true;
                break;
            }
        }
        languageSelected = (support) ? idiomaUsuario : new Locale("es");
        System.out.println(languageSelected);
    }

    public Locale getLanguageSelect() {
        return languageSelected;
    }

    public void setLanguageSelect(Locale languageSelected) {
        this.languageSelected = languageSelected;
    }

    public List<Locale> getSupportLanguages() {
        List<Locale> idiomas = new ArrayList<>();
        Iterator<Locale> it = FacesContext.getCurrentInstance().getApplication().getSupportedLocales();
        while (it.hasNext()) {
            idiomas.add(it.next());
        }
        return idiomas;
    }

    public String cambiarIdioma(Locale idioma) {
        if (idioma != null) {
            this.languageSelected = idioma;
            FacesContext.getCurrentInstance().getViewRoot().setLocale(languageSelected);
        }
        return "";
    }

    public Locale getLanguageSelected() {
        return languageSelected;
    }
    
    

}

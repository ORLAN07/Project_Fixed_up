/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dao;

import java.util.List;
import java.util.Map;
import javax.ejb.Local;
import org.entidades.Persona;

/**
 *
 * @author David
 */
@Local
public interface PersonaFacadeLocal {

    void create(Persona persona);

    void edit(Persona persona);

    void remove(Persona persona);

    Persona find(Object id);

    List<Persona> findAll();

    List<Persona> findRange(int[] range);

    int count();

    Persona iniciarSesion(int documento, String password);

    List<Persona> filtroMultiCriterio(Map<String, Object> usuariosFiltro);

    Persona recuperacontrasena(String email);
    
    Boolean loadUsuarios(String pathFile);
    
    List<Persona> listarVendedorAdminRoot();
    
    List<Persona> listarRootAdmin();

}

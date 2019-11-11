/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dao;

import java.util.List;
import javax.ejb.Local;
import org.entidades.Dificultad;

/**
 *
 * @author David
 */
@Local
public interface DificultadFacadeLocal {

    void create(Dificultad dificultad);

    void edit(Dificultad dificultad);

    void remove(Dificultad dificultad);

    Dificultad find(Object id);

    List<Dificultad> findAll();

    List<Dificultad> findRange(int[] range);

    int count();
    
}

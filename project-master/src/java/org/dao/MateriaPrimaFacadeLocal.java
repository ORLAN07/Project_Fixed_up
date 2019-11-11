/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dao;

import java.util.List;
import javax.ejb.Local;
import org.entidades.MateriaPrima;

/**
 *
 * @author David
 */
@Local
public interface MateriaPrimaFacadeLocal {

    void create(MateriaPrima materiaPrima);

    void edit(MateriaPrima materiaPrima);

    void remove(MateriaPrima materiaPrima);

    MateriaPrima find(Object id);

    List<MateriaPrima> findAll();

    List<MateriaPrima> findRange(int[] range);

    int count();
    
    List<MateriaPrima> listarPorStockMayor0();
    
}

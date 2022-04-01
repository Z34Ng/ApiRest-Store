/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ecommerce.apireststore.service;

import com.ecommerce.apireststore.entity.Orden;
import com.ecommerce.apireststore.security.entity.Usuario;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author ZEAN
 */
public interface IOrdenService {    
    List<Orden> findAll();
    Orden save(Orden o); //retorna una orden que se guarda
    String getNumeroOrden();    
    Optional<Orden> findById(Integer  id);    
    List<Orden> findByUser(Usuario usuario); //retorna ordenes de un usuario
    //el nombre del metodo es de acuerdo al nombre de la variable por la que se quiere trabajar
    void delete(Integer id);
}

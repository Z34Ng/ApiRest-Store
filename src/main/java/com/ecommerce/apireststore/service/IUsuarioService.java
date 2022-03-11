/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ecommerce.apireststore.service;

import com.ecommerce.apireststore.model.Usuario;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author ZEAN
 */

public interface IUsuarioService {
    Optional<Usuario> findById(int id);
    Usuario save(Usuario usuario); 
    Optional<Usuario> findByEmail(String email);
    List<Usuario> findAll();
}

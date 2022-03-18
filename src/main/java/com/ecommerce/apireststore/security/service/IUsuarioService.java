/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ecommerce.apireststore.security.service;

import com.ecommerce.apireststore.security.entity.Usuario;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author ZEAN
 */
public interface IUsuarioService {
    Optional<Usuario> findById(int id);
    Optional<Usuario> findByUsername();
    Optional<Usuario> findByEmail(String email);
    Usuario save(Usuario usuario);
    List<Usuario> findAll();
    boolean existsByUsername();
    boolean existsByEmail();
}

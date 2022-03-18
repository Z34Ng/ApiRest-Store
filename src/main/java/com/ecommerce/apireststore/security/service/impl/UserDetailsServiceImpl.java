/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.apireststore.security.service.impl;

import com.ecommerce.apireststore.security.entity.Usuario;
import com.ecommerce.apireststore.security.entity.UsuarioPrincipal;
import com.ecommerce.apireststore.security.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author ZEAN
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    IUsuarioService usuarioService;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario nuevoUsuario = usuarioService.findByUsername(username).get();
        return UsuarioPrincipal.build(nuevoUsuario);
    }    
}

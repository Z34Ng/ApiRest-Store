/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.apireststore.service;

import com.ecommerce.apireststore.model.Usuario;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author ZEAN
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService{

    @Autowired
    private IUsuarioService usuarioService;    
    
    @Autowired
    HttpSession session; //guarda sesion del usuario logueado
    
    //busca el usuario mediante la base de datos por Spring security  
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> optionalUser = usuarioService.findByEmail(username);
        
        if(optionalUser.isPresent()){
            session.setAttribute("idusuario",optionalUser.get().getId());
            Usuario usuario=optionalUser.get();
            
            return User.builder()
                    .username(usuario.getName())                    
                    .password(usuario.getPassword())
                    .roles(usuario.getTypeUser())
                    .build();
        }
        else{
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
    }    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.apireststore.util;

import com.ecommerce.apireststore.security.entity.Rol;
import com.ecommerce.apireststore.security.enums.RolNombre;
import com.ecommerce.apireststore.security.service.IRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 *
 * @author ZEAN
 */
@Component
public class CreateRoles implements CommandLineRunner{

    @Autowired
    IRolService rolService;
    
    @Override
    public void run(String... args) throws Exception {
        Rol rolAdmin = new Rol(RolNombre.ROLE_ADMIN);
        Rol rolUser = new Rol(RolNombre.ROLE_USER);
        
        rolService.save(rolAdmin);
        rolService.save(rolUser);
    }    
}

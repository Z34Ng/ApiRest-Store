/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.apireststore.security.service.impl;

import com.ecommerce.apireststore.security.entity.Rol;
import com.ecommerce.apireststore.security.repository.IRolRepository;
import com.ecommerce.apireststore.security.service.IRolService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author ZEAN
 */
public class RolServiceImpl implements IRolService {

    @Autowired
    IRolRepository rolRepository;
    
    @Override
    public Rol save(Rol rol) {
        return rolRepository.save(rol);
    }

    @Override
    public Optional<Rol> findByRolNombre(String rolNombre) {
        return rolRepository.findByRolNombre(rolNombre);
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ecommerce.apireststore.security.service;

import com.ecommerce.apireststore.security.entity.Rol;
import com.ecommerce.apireststore.security.enums.RolNombre;
import java.util.Optional;

/**
 *
 * @author ZEAN
 */
public interface IRolService {
    Optional<Rol> findByRolNombre(RolNombre rol);
    Rol save(Rol rolNombre);
}

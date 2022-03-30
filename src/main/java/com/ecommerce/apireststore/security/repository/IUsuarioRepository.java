/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ecommerce.apireststore.security.repository;

import com.ecommerce.apireststore.security.entity.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ZEAN
 */
@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Integer>{
    //Por defectto JPA obtiene un registro por id, pero se puede modificar
    //obtiene un registro por email
    Optional<Usuario> findByEmail(String email);       
    Optional<Usuario> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    
}

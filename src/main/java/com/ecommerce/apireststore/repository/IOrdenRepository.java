/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ecommerce.apireststore.repository;

import com.ecommerce.apireststore.entity.Orden;
import com.ecommerce.apireststore.security.entity.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ZEAN
 */
@Repository
public interface IOrdenRepository extends JpaRepository<Orden,Integer>{
    List<Orden> findByUser(Usuario usuario);
}

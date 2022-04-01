/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ecommerce.apireststore.service;

import com.ecommerce.apireststore.entity.Producto;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author ZEAN
 */
public interface IProductoService {
    Producto save(Producto producto);
    Optional<Producto> findProducto(Integer  id);
    Optional<Producto> findByName(String name);
    boolean existsByName(String name);
    void update(Producto producto);
    void delete(Integer  id);
    List<Producto> findAll();    
}

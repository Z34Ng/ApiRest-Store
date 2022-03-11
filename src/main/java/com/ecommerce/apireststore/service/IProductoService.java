/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ecommerce.apireststore.service;

import com.ecommerce.apireststore.model.Producto;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author ZEAN
 */
public interface IProductoService {
    public Producto save(Producto producto);
    public Optional<Producto> findProducto(Integer  id);  
    public void update(Producto producto);
    public void delete(Integer  id);
    public List<Producto> findAll();
    
}

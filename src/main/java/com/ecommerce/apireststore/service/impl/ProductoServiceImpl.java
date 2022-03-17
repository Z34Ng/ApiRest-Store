/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.apireststore.service.impl;

import com.ecommerce.apireststore.entity.Producto;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ecommerce.apireststore.repository.IProductoRepository;
import com.ecommerce.apireststore.service.IProductoService;

/**
 *
 * @author ZEAN
 */
@Service //para inyectar al contraldor  los metodos
public class ProductoServiceImpl implements IProductoService{

    @Autowired //inyecta a la clase este objeto
    private IProductoRepository productoRepository;
    
    @Override
    public Producto save(Producto producto){
        return productoRepository.save(producto);        
    }

    @Override
    public Optional<Producto> findProducto(Integer id) {
        return productoRepository.findById(id);        
    }

    @Override
    public void update(Producto producto) {
        productoRepository.save(producto);
    }

    @Override
    public void delete(Integer id) {
        productoRepository.deleteById(id);
    }

    @Override
    public List<Producto> findAll() {
        return productoRepository.findAll();
    }
    
}

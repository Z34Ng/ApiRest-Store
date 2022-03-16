/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.apireststore.controller;

import com.ecommerce.apireststore.entity.Producto;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ecommerce.apireststore.service.IProductoService;
import com.ecommerce.apireststore.service.IUsuarioService;
import java.util.Comparator;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ZEAN
 */
@RestController
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    private IProductoService productoService;

    @Autowired
    private IUsuarioService usuarioService;
    
    @PostMapping("/save")    
    public Producto saveProduct(@RequestBody Producto producto) throws IOException {        
        producto.setUser(usuarioService.findById(1).get());                
        return productoService.save(producto);
    }
    
    @GetMapping("/getAll")
    public List<Producto> showAll(){
        List<Producto> productos=productoService.findAll();
        productos.sort((Comparator.comparingInt(Producto::getId)).reversed());
        return productos;
    }
    
    @GetMapping("/{id}")
    public Producto getProductById(@PathVariable int id) {
        return productoService.findProducto(id).get();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {                                       
        productoService.delete(id);
        return new ResponseEntity<>("Done",HttpStatus.OK);
    }
}

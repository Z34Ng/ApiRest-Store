/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.apireststore.controller;

import com.ecommerce.apireststore.entity.DetalleOrden;
import com.ecommerce.apireststore.entity.Orden;
import com.ecommerce.apireststore.entity.Producto;
import com.ecommerce.apireststore.service.IOrdenService;
import com.ecommerce.apireststore.service.IProductoService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ZEAN
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
    
    @Autowired
    IOrdenService ordenService;
    
    @Autowired
    IProductoService productoService;
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/deleteOrder/{idOrder}")
    public ResponseEntity<String> deleteOrder(@PathVariable int idOrder){
        aumentarStockXOrden(idOrder);
        ordenService.delete(idOrder);
        return new ResponseEntity("Delete order with id "+idOrder,HttpStatus.OK);
    } 
    
    private void aumentarStockXOrden(int idOrder){
        Optional<Orden> orden = ordenService.findById(idOrder);
        List<DetalleOrden> detalles = orden.get().getDetalles();
        for(DetalleOrden dt : detalles){
            Producto producto = productoService.findByName(dt.getProductName()).get();
            producto.setStock(producto.getStock()+dt.getAmount());
            productoService.save(producto);
        }
    }
}

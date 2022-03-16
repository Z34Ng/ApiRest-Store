/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.apireststore.controller;

import com.ecommerce.apireststore.entity.Orden;
import com.ecommerce.apireststore.entity.Usuario;
import com.ecommerce.apireststore.service.IOrdenService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ecommerce.apireststore.service.IProductoService;
import com.ecommerce.apireststore.service.IUsuarioService;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/administrador")
public class AdminController {
    
    @Autowired 
    private IProductoService productoService;
    
    @Autowired 
    private IUsuarioService usuarioService;
    
    @Autowired 
    private IOrdenService ordenService;
    /*
    @GetMapping("")
    public String home(Model model){
        List<Producto> productos=productoService.findAll();
        model.addAttribute("productos",productos);
        return "administrador/home";    
    }
*/
    @GetMapping("/getUsersList")
    public ResponseEntity<List<Usuario>> getListUser(){  
        List<Usuario> usuarios=usuarioService.findAll();
        return ResponseEntity.ok(usuarios);
    }
    
    @GetMapping("/getOrders")
    public ResponseEntity<List<Orden>> getOrders(Model model){
        List<Orden> ordenes=ordenService.findAll();
        return ResponseEntity.ok(ordenes);
    }
    /*
    @GetMapping("/getDetailsOrder/{id}")
    public String getDetailsOrder(@PathVariable int id, Model model){
        Optional<Orden> orden= ordenService.findById(id);
        
        model.addAttribute("detallesOrden", orden.get().getDetalles());        
        return "administrador/detalleorden";
    }
    */
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.apireststore.controller;

import com.ecommerce.apireststore.entity.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ecommerce.apireststore.service.IProductoService;
import com.ecommerce.apireststore.security.service.IUsuarioService;
import com.ecommerce.apireststore.service.aws.AwsS3Service;
import java.util.Comparator;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
    
    @Autowired
    private AwsS3Service awss3Service;
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(path="/save", consumes={MediaType.MULTIPART_FORM_DATA_VALUE})    
    public Producto saveProduct(@RequestPart Producto producto, @RequestPart MultipartFile img){                        
        if(!img.isEmpty()){
            String key=awss3Service.uploadFile(img);
            producto.setImgKey(key);
            producto.setPicture(awss3Service.getFileUrl(key));
        }
        else{
            producto.setImgKey("default.jpg");
            producto.setPicture(awss3Service.getFileUrl(producto.getImgKey()));
        }
        UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext()
                                                .getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        producto.setUser(usuarioService.findByUsername(username).get());                
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

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
        String key=productoService.findProducto(id).get().getImgKey();
        if(!key.equals("default.jpg"))
            awss3Service.deleteFile(key);
        productoService.delete(id);
        return new ResponseEntity<>("Done",HttpStatus.OK);
    }    
}

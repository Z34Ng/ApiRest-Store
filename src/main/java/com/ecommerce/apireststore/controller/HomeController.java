/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.apireststore.controller;

import com.ecommerce.apireststore.dto.DetalleOrdenDto;
import com.ecommerce.apireststore.entity.DetalleOrden;
import com.ecommerce.apireststore.entity.Orden;
import com.ecommerce.apireststore.entity.Producto;
import com.ecommerce.apireststore.security.entity.Usuario;
import com.ecommerce.apireststore.service.IDetalleOrdenService;
import com.ecommerce.apireststore.service.IOrdenService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ecommerce.apireststore.service.IProductoService;
import com.ecommerce.apireststore.security.service.IUsuarioService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ZEAN
 */
@RestController
@RequestMapping("")
public class HomeController {

    @Autowired
    private IProductoService productoService;

    @Autowired
    private IOrdenService ordenService;

    @Autowired
    private IDetalleOrdenService detalleOrdenService;

    @Autowired
    private IUsuarioService usuarioService;

    @PreAuthorize("hasRole('USER')")
    @GetMapping(path = "/saveorder")
    public ResponseEntity<Orden> saveOrder(@Valid @RequestBody List<DetalleOrdenDto> detallesDto,
                                           BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity("Campo mal puestos", HttpStatus.BAD_REQUEST);        

        boolean existeTodoProducto = detallesDto.stream().anyMatch(dt -> productoService.existsByName
                                                                         (dt.getProducto().getName()));
        if (!existeTodoProducto)
            return new ResponseEntity("Algun(os) producto(s) no existe(n)", HttpStatus.NOT_FOUND);        

        boolean stockTodoProducto = detallesDto.stream().anyMatch(dt -> dt.getAmount() <= productoService
                                                .findByName(dt.getProducto().getName()).get().getStock());
        if (!stockTodoProducto)
            return new ResponseEntity("Algun(os) producto(s) no tiene(n) stock", HttpStatus.BAD_REQUEST);                        
        
        Optional<Usuario> user = getOptionalUserFromToken();
        if (user.isEmpty())
            return new ResponseEntity("El usuario no existe", HttpStatus.NOT_FOUND);
        
        disminuirStockxDetalle(detallesDto);
        List<DetalleOrden> detalles = createDetalleOrdenFromDto(detallesDto);
        double precioTotalOrden = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();
        
        Orden orden = createOrden(precioTotalOrden,user.get());
        
        ordenService.save(orden);
        guardarDetalleOrden(detalles, orden);

        return new ResponseEntity(orden, HttpStatus.OK);
    }

    private List<DetalleOrden> createDetalleOrdenFromDto(List<DetalleOrdenDto> detalleDto) {
        List<DetalleOrden> detallesOrden = new ArrayList<>();       
        
        for (DetalleOrdenDto dt : detalleDto) {
            DetalleOrden detalle = new DetalleOrden(dt.getProducto().getName(),
                                                    dt.getAmount(),
                                                    dt.getAmount()*dt.getProducto().getPrice());            
            detalle.setProducto(productoService.findByName(dt.getProducto()
                                                             .getName()).get());            
            detallesOrden.add(detalle);            
        }
        return detallesOrden;
    }

    private Orden createOrden(double precioTotalOrden, Usuario usuario){
        Orden orden=new Orden(ordenService.getNumeroOrden(), LocalDate.now(),precioTotalOrden);        
        //orden.setReciedDate(LocalDate.MIN);
        orden.setUser(usuario);
        return orden;
    }
    
    private void guardarDetalleOrden(List<DetalleOrden> detalles, Orden orden) {
        for (DetalleOrden detalle : detalles) {
            detalle.setOrden(orden);
            detalleOrdenService.save(detalle);
        }
    }

    private Optional<Usuario> getOptionalUserFromToken(){
        UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext()
                                                .getAuthentication().getPrincipal();
        
        return usuarioService.findByUsername(userDetails.getUsername());
    }
    
    private void disminuirStockxDetalle(List<DetalleOrdenDto> detalleDto){
        detalleDto.stream().forEach(dt->disminuirStockxProducto(dt.getProducto(),dt.getAmount()));
    }
    
    private void disminuirStockxProducto(Producto producto, int amount){
        Producto p=productoService.findByName(producto.getName()).get();
        p.setStock(p.getStock()-amount);
        productoService.save(p);
    }
    
    @PostMapping("/search")
    public ResponseEntity<List<Producto>> search(@RequestParam String productName) { //debe llamarse igual a la variable enviada por formulario
        List<Producto> matchProductos = productoService.findAll().stream()
                .filter(p -> p.getName().contains(productName))
                .collect(Collectors.toList());
        return new ResponseEntity(matchProductos, HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/myorders")
    public ResponseEntity<List<Orden>> getMyOrders(){
        Optional<Usuario> u=getOptionalUserFromToken();
        if (u.isEmpty())
            return new ResponseEntity("El usuario no existe", HttpStatus.NOT_FOUND);
        
        Usuario user=u.get();        
        return new ResponseEntity(user.getOrdenes(),HttpStatus.OK);
    }
}

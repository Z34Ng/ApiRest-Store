/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.apireststore.controller;

import com.ecommerce.apireststore.model.Orden;
import com.ecommerce.apireststore.model.Producto;
import com.ecommerce.apireststore.model.Usuario;
import com.ecommerce.apireststore.service.IOrdenService;
import com.ecommerce.apireststore.service.IUsuarioService;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    private final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private IUsuarioService usuarioService;
    
    @Autowired
    private IOrdenService ordenService;   
    
    BCryptPasswordEncoder passEncoder = new BCryptPasswordEncoder();
    
    @PostMapping("/save")
    public Usuario registrar(@RequestBody Usuario usuario){
        usuario.setTypeUser("USER");
        usuario.setPassword(passEncoder.encode(usuario.getPassword()));  
        
        return usuarioService.save(usuario);
    }
    
    @GetMapping("/getAll")
    public List<Usuario> getAllUsers(){                
        return usuarioService.findAll();
    }
    /*
    @GetMapping("/login")
    public String login(Usuario usuario, HttpSession session){
        Optional<Usuario> user = usuarioService.findById(
                                    Integer.parseInt(
                                    session.getAttribute("idusuario")
                                            .toString()));  
        if(user.isPresent()){
            session.setAttribute("idusuario", user.get().getId()); 
            if(user.get().getTypeUser().equals("ADMIN"))
                return "redirect:/administrador";
            else
                return "redirect:/";
        }
        else
            LOGGER.info("Usuario no existe");
        return "redirect:/";
    }
    
    @GetMapping("/getPurchases")
    public ResponseEntity<List<Orden>> getPurchases(HttpSession session){
        String usuarioID=session.getAttribute("idusuario").toString();
        Usuario user=usuarioService.findById(Integer.parseInt(usuarioID)).get();
                
        List<Orden> ordenes = ordenService.findByUser(user);
                
        return ResponseEntity.ok(ordenes);
    }  
*/
}

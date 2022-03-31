/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.apireststore.security.entity;

import com.ecommerce.apireststore.entity.Orden;
import com.ecommerce.apireststore.entity.Producto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 *
 * @author ZEAN
 */
@Entity
@Data
@Table(name="usuario")
public class Usuario implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    @JsonIgnore
    private int id;    
    private String name;
    private String lastname;
    
    @Column(unique=true)
    private String username;    
    private String email;    
    private String address;    
    private String phone;
    @JsonIgnore
    private String password;
    
    @NotNull//se puede quitar esta anotacion
    @ManyToMany(fetch=FetchType.EAGER)
    @JsonIgnore
    @JoinTable(
        name="usuario_rol", //se crea tabla "usuario_rol" que guarda las relaciones
        joinColumns=@JoinColumn(name="usuario_id"),//se crea el campo que hace referencia al id de usuario
        inverseJoinColumns=@JoinColumn(name="rol_id"))        
    private Set<Rol> roles = new HashSet<>(); // no permite elementos duplicados y no tiene orden entre sus elementos.
    
    @JsonIgnore
    @OneToMany(mappedBy="user")
    private List<Producto> productos;
    
    @JsonIgnore
    @OneToMany(mappedBy="user")
    private List<Orden> ordenes;
    
    public Usuario(String name, String lastname, String username, String email, 
                                String address,  String phone, String password) {        
        this.name = name;    
        this.lastname = lastname;
        this.email = email;
        this.username = username;
        this.address = address;
        this.phone = phone;         
        this.password = password;
    }
    
    public Usuario(){
    }
}

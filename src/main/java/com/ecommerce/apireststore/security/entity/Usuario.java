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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
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
    private int id;
    
    @NotEmpty    
    @Size(min=2, max=22, message="Name incorrect")
    @Pattern(regexp="[A-Za-z]")
    private String name;
    
    @NotEmpty
    @Size(min=2, max=22, message="Lastname incorrect")
    @Pattern(regexp="[A-Za-z]")
    private String lastname;
    
    @NotEmpty
    @Email
    @Column(unique=true)
    private String email;
    
    @NotBlank //la propiedad no sea nula ni espacio en blanco. (solo cadenas)
    private String address;
    
    @NotBlank
    @Size(min=6, max=9)
    @Pattern(regexp = "[9][0-9]{9}") //inicia con 9, solo números, 9 caracteres
    private String phone;
    
    @NotBlank
    private String password;
    
    @NotEmpty
    @ManyToMany 
    @JoinTable(
        name="usuario_rol", //se crea tabla "usuario_rol" que guarda las relaciones
        joinColumns=@JoinColumn(name="usuario_id"),//se crea el campo que hace referencia al id de usuario
        inverseJoinColumns=@JoinColumn(name="rol_id"))        
    private Set<Rol> roles = new HashSet<>(); // no permite elementos duplicados y no tiene orden entre sus elementos.
    
    @JsonIgnore
    @OneToMany(mappedBy="user")
    private List<Producto> productos;
    
    @OneToMany(mappedBy="user")
    private List<Orden> ordenes;
    
    public Usuario(int id, String name, String email, String address, String lastname, String phone, String password) {
        this.id = id;
        this.name = name;    
        this.lastname=lastname;
        this.email = email;
        this.address = address;
        this.phone = phone;         
        this.password = password;
    }
    
    public Usuario(){        
    }
}

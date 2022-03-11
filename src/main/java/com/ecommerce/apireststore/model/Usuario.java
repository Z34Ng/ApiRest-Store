/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.apireststore.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
    private String name;     
    private String email;    
    private String address;    
    private String phone;    
    private String typeUser;       
    private String password;
    
    @JsonIgnore
    @OneToMany(mappedBy="user")
    private List<Producto> productos; 
    
    @OneToMany(mappedBy="user")
    private List<Orden> ordenes;
    
    public Usuario(int id, String name, String email, String address, String phone, String typeUser, String password) {
        this.id = id;
        this.name = name;        
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.typeUser = typeUser;
        this.password = password;
    }
    
    public Usuario(){        
    }
}

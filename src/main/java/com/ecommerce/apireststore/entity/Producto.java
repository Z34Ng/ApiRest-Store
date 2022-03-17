/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.apireststore.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import com.ecommerce.apireststore.security.entity.Usuario;
/**
 *
 * @author ZEAN
 */
@Entity
@Data
@Table(name="producto")

public class Producto implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private String picture;
    private double price;
    private int amount;
    
    @ManyToOne
    private Usuario user;

    public Producto() {
    }
    
    public Producto(Integer id, String name, String description, String picture, double price, int amount, Usuario user) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.picture = picture;
        this.price = price;
        this.amount = amount;
        this.user = user;
    }        
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.apireststore.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author ZEAN
 */
@Entity 
@Data
@Table(name="detalleorden")
public class DetalleOrden implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String name; 
    private double amount; 
    private double prize; 
    private double total; 

    @ManyToOne
    private Orden orden;
    
    @ManyToOne
    private Producto producto;

    public DetalleOrden(int id, String name, double amount, double prize, double total) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.prize = prize;
        this.total = total;
    }

    public DetalleOrden() {
    }
}

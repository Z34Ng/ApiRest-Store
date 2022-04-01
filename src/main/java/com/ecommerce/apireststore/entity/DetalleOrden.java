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
    private String productName;
    private int amount; 
    private double total; 

    @ManyToOne
    private Orden orden;
    
    @ManyToOne
    private Producto producto;

    public DetalleOrden(String productName, int amount, double total) {        
        this.productName = productName;
        this.amount = amount;
        this.total = total;
    }

    public DetalleOrden(){
    }
}

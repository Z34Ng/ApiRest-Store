/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.apireststore.security.entity;

import com.ecommerce.apireststore.security.enums.RolNombre;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 *
 * @author ZEAN
 */
@Entity
@Data
@Table(name="rol")
public class Rol implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    
    @NotNull
    @Enumerated(EnumType.STRING) //se debe definir el tipo ya que por defecto se crea como int
    private RolNombre rolNombre;
   
    public Rol(){        
    }
    
    public Rol(@NotNull RolNombre rolNombre){
        this.rolNombre=rolNombre;
    }
}

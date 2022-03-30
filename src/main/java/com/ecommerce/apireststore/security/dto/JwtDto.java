/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.apireststore.security.dto;

/**
 *
 * @author ZEAN
 */
public class JwtDto {
    private String token;
    
    public JwtDto(String token) {
        this.token = token;
    }
    
    public JwtDto(){        
    }

    public void setToken(String token) {
        this.token = token;
    }        

    public String getToken() {
        return token;
    }
    
}

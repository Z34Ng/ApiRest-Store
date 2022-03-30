/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.apireststore.security.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import lombok.Data;

/**
 *
 * @author ZEAN
 */
@Data
public class LoginUsuario {
    @NotBlank
    //@Pattern(regexp="[A-Za-z0-9]")
    private String username; 
    
    @NotBlank
    private String password;
}

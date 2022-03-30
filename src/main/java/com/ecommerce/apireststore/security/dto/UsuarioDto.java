/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.apireststore.security.dto;

import java.util.HashSet;
import java.util.Set;
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
@Data
public class UsuarioDto {
    @NotBlank    
    //@Pattern(regexp="[A-Za-z]")
    @Size(min=2, max=22)    
    private String name;           
    
    @NotBlank    
    //@Pattern(regexp="[A-Za-z]")
    @Size(min=2, max=22)    
    private String lastname;     
        
    @Email
    @Size(min=5, max=50)
    private String email;
    
    @NotEmpty
    //@Pattern(regexp="[A-Za-z0-9]")
    private String username;         
    
    @NotBlank
    private String password;
    
    @NotBlank //la propiedad no sea nula ni espacio en blanco. (solo cadenas)
    private String address;
    
    @NotBlank        
    //@Pattern(regexp = "[9][0-9]{9}") //inicia con 9, solo números, 9 caracteres    
    @Size(min=6, max=9)
    private String phone;    

    private Set<String> roles  = new HashSet<>();
}

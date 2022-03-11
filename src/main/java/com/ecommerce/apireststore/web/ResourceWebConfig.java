/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.apireststore.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * @author ZEAN
 */
@Configuration 
//path relativos
//configuracion que permite apuntar desde cualquier lugar al path de imagenes
public class ResourceWebConfig implements WebMvcConfigurer{
    /**
     * Permite obtener y visualizar imagenes en la vista home.html
     * @param registry 
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**").addResourceLocations("file:images/"); //images/** direccion q obtendra todo lo que esta en la carpeta 
        //addresourcelocation indica a donde deberia aountar
    }        
}

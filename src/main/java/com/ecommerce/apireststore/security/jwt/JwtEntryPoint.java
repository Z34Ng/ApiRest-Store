/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.apireststore.security.jwt;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 *
 * @author ZEAN
 */
//Comprueba que un token sea valido
@Component
public class JwtEntryPoint implements AuthenticationEntryPoint{

    private final static Logger LOG = LoggerFactory.getLogger(JwtEntryPoint.class);
       
    
    //Rechaza todas las peticiones que no esten autenticadas
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        LOG.error("Error en metodo commece");
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"No autorizado");
    }
    
}

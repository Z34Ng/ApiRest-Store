/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.apireststore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author ZEAN
 * gilson@gmail.com::1234
 */
@Configuration
@EnableWebSecurity //"interceptor", restringe acceso a paginas de acuerdo al tipo de usuario
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    
    @Autowired
    private UserDetailsService userDetailService;
    
    //Valida el usuario correcto
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(getEncoder());//invocacion del metodo para ejecución
    }
        
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //csrf() permite que no inyecte codigo malicioso en la aplicacion
        http.csrf().disable();
        /*
                .authorizeRequests()         
                .antMatchers("/administrador/**").hasRole("ADMIN") //siempre valida que ingrese a las rutas como admin
                .antMatchers("/productos/**").hasRole("ADMIN")
                .and()
                .formLogin().loginPage("/usuario/login")
                .permitAll().defaultSuccessUrl("/usuario/acceder"); //cuando es usuario USER se le permite siempre el resto
                */
    }
    
    @Bean
    public BCryptPasswordEncoder getEncoder(){
        return new BCryptPasswordEncoder();
    }
}

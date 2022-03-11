/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.apireststore.service;

import com.ecommerce.apireststore.model.Orden;
import com.ecommerce.apireststore.model.Usuario;
import com.ecommerce.apireststore.repository.IOrdenRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ZEAN
 */
@Service
public class OrdenServiceImpl implements IOrdenService{

    @Autowired
    private IOrdenRepository ordenRepository;
    
    @Override
    public Orden save(Orden o) {
         return ordenRepository.save(o);
    }

    @Override
    public List<Orden> findAll(){
        return ordenRepository.findAll();
    }
    
    @Override
    public String getNumeroOrden(){
        int num;                
        List<Orden> ordenes=findAll();
        List<Integer> nums=new ArrayList<>();
        
        ordenes.stream().forEach(o->nums.add(Integer.parseInt(o.getNumber())));
        
        if(ordenes.isEmpty())
            num=1;
        else{
            num=nums.stream().max(Integer::compare).get();
            num++;
        }
                
        return generarNumeroOrden(num);
    }
    
    public String generarNumeroOrden(int num){
        String numeroOrden=String.valueOf(num);        
        int numDigitos=numeroOrden.length(); //numero de digitos               
        
        for (int j=numDigitos; j<=9; j++)//añadimos los ceros 
            numeroOrden="0"+numeroOrden;
        
        return numeroOrden;
    }

    @Override
    public List<Orden> findByUser(Usuario usuario) {
        return ordenRepository.findByUser(usuario);
    }

    @Override
    public Optional<Orden> findById(Integer id) {
        return ordenRepository.findById(id);
    }
}
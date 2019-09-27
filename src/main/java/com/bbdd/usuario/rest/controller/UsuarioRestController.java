/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbdd.usuario.rest.controller;

import com.bbdd.usuario.rest.dao.CommonInput;
import com.bbdd.usuario.rest.dao.CommonResponse;
import com.bbdd.usuario.rest.dao.UsuarioDAO;
import com.bbdd.usuario.rest.entity.Usuario;
import io.dropwizard.hibernate.UnitOfWork;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author osboxes
 */

@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioRestController {
    
    private UsuarioDAO dao;
    
    public UsuarioRestController(UsuarioDAO dao){
        this.dao = dao;
    }
    
    @GET
   // @Path("/findAll")
    @UnitOfWork
    public CommonResponse findAll(){
        CommonResponse response = null;
        try {
          System.out.println("11111111111111");
           response.setUsuarios(dao.findAll()); 
        } catch (Exception e) {
            response.setRespuesta(e.getMessage());
        }
        
        return response;
    }
    
    @POST
    @Path("/findById")
    @UnitOfWork
    public Usuario findUsuario(CommonInput input){
        return dao.findById(input.getUsuario());
    }
    
    @POST
    @Path("/create")
    @UnitOfWork
    public Long createUsuario(CommonInput input){
         return dao.createUsuario(input.getUsuario());
    }
    
    @POST
    @Path("/delete")
    @UnitOfWork
    public Long deleteUsuario(CommonInput input){
        return dao.deleteUsuario(input.getUsuario());
    }
}
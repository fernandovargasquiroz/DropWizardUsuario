/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbdd.dropwizardusuario.rest.controller;

import com.bbdd.dropwizardusuario.rest.dao.UsuarioDAO;
import com.bbdd.dropwizardusuario.rest.entity.Usuario;
import io.dropwizard.hibernate.UnitOfWork;
import java.util.List;
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
    @UnitOfWork
    public List<Usuario> findAll(){
        return dao.findAll();
    }
    
    @POST
    @Path("/findById")
    @UnitOfWork
    public Usuario findPerson(Usuario user){
        return dao.findById(user);
    }
    
    @POST
    @Path("/create")
    @UnitOfWork
    public Long createPerson(Usuario user){
         return dao.createUsuario(user);
    }
    
    @POST
    @Path("/delete")
    @UnitOfWork
    public Long deleteUsuario(Usuario user){
        return dao.deleteUsuario(user);
    }
}
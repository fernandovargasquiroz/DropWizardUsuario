/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbdd.usuario.rest.dao;

import com.bbdd.usuario.rest.entity.Usuario;
import io.dropwizard.hibernate.AbstractDAO;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

/**
 *
 * @author osboxes
 */
public class UsuarioDAO extends AbstractDAO<Usuario> {

    public UsuarioDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Usuario findById(Usuario usuario) {
        return get(usuario.getId());
    }

    public long createUsuario(Usuario usuario) {
        return persist(usuario).getId();
    }

    public long deleteUsuario(Usuario usuario) {
        currentSession().delete(usuario);
        return usuario.getId();
    }

    public List<Usuario> findAll() {
        List<Usuario> result=new ArrayList<Usuario>();
        try {
            Query<Usuario> query=query("SELECT u FROM Usuario u");
            System.out.println("22222222 "+query.getResultList());
            for (Usuario usuario : query.getResultList()) {
                System.out.println("usuario: "+usuario.getNombre());
                result.add(usuario);
            }
             System.out.println("result: "+result.size());
            query.setFetchSize(0);
           
        return result;
        } catch (Exception e) {
            System.out.println("Error ::::::::::::::::::::::::::"+e.getMessage());
        }
        return result;
     
    }
}

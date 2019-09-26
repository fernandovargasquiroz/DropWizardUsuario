/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbdd.usuario.rest.dao;

import com.bbdd.usuario.rest.entity.Usuario;
import io.dropwizard.hibernate.AbstractDAO;
import java.util.List;
import org.hibernate.SessionFactory;

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
        return findAll();
    }
}
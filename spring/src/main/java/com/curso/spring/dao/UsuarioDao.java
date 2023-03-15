package com.curso.spring.dao;

import java.util.List;

import com.curso.spring.models.Usuario;

public interface UsuarioDao {

	List<Usuario>getUsuarios();

	void eliminar(Long id);

	void registrar(Usuario usuario);

	Usuario obtenerUsuario(Usuario usuario);
	
	
}

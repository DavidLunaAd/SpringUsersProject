package com.curso.spring.dao;

import java.util.List;

import com.curso.spring.models.Usuario;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
@Transactional
public class UsuarioDaoImpl implements UsuarioDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Usuario> getUsuarios() {
		String query = "FROM Usuario";
		return entityManager.createQuery(query).getResultList();
		
	}

	@Override
	public void eliminar(Long id) {
		Usuario usuario = entityManager.find(Usuario.class, id); 
		entityManager.remove(usuario);
	}

	@Override
	public void registrar(Usuario usuario) {
		entityManager.merge(usuario);
	}

	@Override
	public Usuario obtenerUsuario(Usuario usuario) {
		String query = "FROM Usuario WHERE email = :email";
		
		List<Usuario> lista =  entityManager.createQuery(query)
					.setParameter("email", usuario.getEmail())
					.getResultList();
		
		if(lista.isEmpty()) {
			return null;
		}
		
		String passHash = lista.get(0).getPassword();
		
		Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
		if(argon2.verify(passHash, usuario.getPassword().toCharArray())) {
			return lista.get(0);
		}
			return null;
					
	}
}

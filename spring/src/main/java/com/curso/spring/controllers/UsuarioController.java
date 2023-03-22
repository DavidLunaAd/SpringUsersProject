package com.curso.spring.controllers;

import java.util.ArrayList;
import java.util.List;

import com.curso.spring.dao.UsuarioDao;
import com.curso.spring.models.Usuario;
import com.curso.spring.utils.JWTUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

@RestController
public class UsuarioController {
	
	@Autowired
	private UsuarioDao usuarioDao;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@RequestMapping(value= "api/usuarios", method = RequestMethod.GET)
	public List<Usuario> getUsuarios(@RequestHeader(value="Authorization") String token) {
		
//		String usuarioId = jwtUtil.getKey(token);
//		if(usuarioId == null) {
//			return new ArrayList<>();
//		}
//		return usuarioDao.getUsuarios();
//	}
//		if (!validarToken(token)) { return null; }

	        return usuarioDao.getUsuarios();
	    }

	    private boolean validarToken(String token) {
	        String usuarioId = jwtUtil.getKey(token);
	        return usuarioId != null;
	    }
	
	@RequestMapping(value = "api/usuario/{id}", method = RequestMethod.DELETE)
	public void eliminarUsuario(@PathVariable Long id) {
			
		usuarioDao.eliminar(id);
	}

	@RequestMapping(value="api/registrar",  method = RequestMethod.POST)
	public void registrarUsuario(@RequestBody Usuario usuario) {
		Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
		String hash = argon2.hash(1, 1024, 1, usuario.getPassword());
		usuario.setPassword(hash);		
		usuarioDao.registrar(usuario);
	}

}

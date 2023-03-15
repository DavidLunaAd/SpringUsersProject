package com.curso.spring.controllers;

import com.curso.spring.dao.UsuarioDao;
import com.curso.spring.models.Usuario;
import com.curso.spring.utils.JWTUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
	
	@Autowired 
	private UsuarioDao usuarioDao; 
	
	@Autowired
	private JWTUtil jwtutil;
	
	@RequestMapping(value="api/login",  method = RequestMethod.POST)
	public String login(@RequestBody Usuario usuario) {
		
		Usuario usuarioLog = usuarioDao.obtenerUsuario(usuario);
		if (usuarioLog != null) {
			String tokenJwt = jwtutil.create(String.valueOf(usuarioLog.getId()), usuarioLog.getEmail());
			return tokenJwt;
		}
		return "FAIL";
	}

}

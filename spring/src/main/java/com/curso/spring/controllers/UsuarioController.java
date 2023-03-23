package com.curso.spring.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.curso.spring.dao.UsuarioDao;
import com.curso.spring.dao.UsuarioService;
import com.curso.spring.models.Usuario;
import com.curso.spring.utils.Connection;
import com.curso.spring.utils.JWTUtil;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

@RestController
public class UsuarioController {
	
	@Autowired
	private UsuarioDao usuarioDao;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	 @Autowired
	    UsuarioService usuarioService;
	 
	 	@RequestMapping(value= "api/usuarios", method = RequestMethod.GET)
		public List<Usuario> getUsuarios() throws InterruptedException, ExecutionException {
		
		       return usuarioService.obtenerTodosLosUsuarios();
		    }
	 	
	 	 @RequestMapping(value="api/registrar",  method = RequestMethod.POST)
		    public String registrarUsuario(@RequestBody Usuario usuario ) throws InterruptedException, ExecutionException {
		        return usuarioService.registrar(usuario);
		    }
	 	 
	  	@RequestMapping(value = "/deletePatient/{id}", method = RequestMethod.DELETE)
	    public String eliminar(@PathVariable String id){
	        return usuarioService.eliminar(id);
	    }
	
//	@RequestMapping(value= "api/usuario", method = RequestMethod.GET)
//	public List<Usuario> getUsuarios(@RequestHeader(value="Authorization") String token) {
//		
//		String usuarioId = jwtUtil.getKey(token);
//		if(usuarioId == null) {
//			return new ArrayList<>();
//		}
//		return usuarioDao.getUsuarios();
//	}
//		if (!validarToken(token)) { return null; }

//	        return usuarioDao.getUsuarios();
//	    }

//	    private boolean validarToken(String token) {
//	        String usuarioId = jwtUtil.getKey(token);
//	        return usuarioId != null;
//	    }
	 	 
	
	
//	@RequestMapping(value = "api/usuario/{id}", method = RequestMethod.DELETE)
//	public void eliminarUsuario(@PathVariable Long id) {
//			
//		usuarioDao.eliminar(id);
//	}

//	@RequestMapping(value="api/registra",  method = RequestMethod.POST)
//	public void registraUsuario(@RequestBody Usuario usuario) {
//		Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
//		String hash = argon2.hash(1, 1024, 1, usuario.getPassword());
//		usuario.setPassword(hash);		
//		usuarioDao.registrar(usuario);
//		}
		
		
		
	
//	CollectionReference reference;
//	static Firestore db;
//	
//	public static boolean registrar(String coleccion, String documento, Map<String, Object> data){
//		
//		db = FirestoreClient.getFirestore();
//		
//		try {
//			DocumentReference docRef = db.collection(coleccion).document(documento);
//			ApiFuture<WriteResult> result = docRef.set(data);
//			System.out.println("Guardado con exito");
//		}catch (Exception e){
//			System.out.println("Error "+ e.getMessage());
//			
//		}
//		return false;
//		
//	}

}

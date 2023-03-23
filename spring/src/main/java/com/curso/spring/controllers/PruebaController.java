package com.curso.spring.controllers;

import java.util.List;
import java.util.concurrent.ExecutionException;

import com.curso.spring.dao.UsuarioService;
import com.curso.spring.models.Usuario;
import com.curso.spring.utils.Connection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PruebaController {
	
	@GetMapping("/prueba")
	public List<String> prueba() {
		return List.of("prueba", "fsdfs", "hola");
		}
	
//	 @Autowired
//	    UsuarioService usuarioService;
//
//
//	    @GetMapping("/getPatientDetails")
//	    public Usuario getPatient(@RequestParam String nombre ) throws InterruptedException, ExecutionException{
//	        return usuarioService.getPatientDetails(nombre);
//	    }
//
//
//	    @PutMapping("/updatePatient")
//	    public String updatePatient(@RequestBody Usuario usuario  ) throws InterruptedException, ExecutionException {
//	        return usuarioService.updatePatientDetails(usuario);
//	    }
//
//	    @DeleteMapping("/deletePatie")
//	    public String deletePatient(@RequestParam String nombre){
//	        return usuarioService.deletePatient(nombre);
//	    }

	}

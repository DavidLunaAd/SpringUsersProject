package com.curso.spring.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PruebaController {
	
	@GetMapping("/prueba")
	public List<String> prueba() {
		return List.of("prueba", "fsdfs", "hola");
		}

}

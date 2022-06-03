package com.mcm.sp.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mcm.sp.entities.Categoria;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaController {
	

	@GetMapping
	public List<Categoria> listar(){ 
		
		Categoria cat1 = new Categoria(1, "Informatica");
		Categoria cat2 = new Categoria(1, "Video game");
		
		List<Categoria> lista = new ArrayList<>();
		lista.add(cat1);
		lista.add(cat2);
		
		
		return lista;
	}
}

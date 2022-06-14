package com.mcm.sp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mcm.sp.entities.Cliente;
import com.mcm.sp.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {
		
	@Autowired
	private ClienteService clienteservice;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Cliente> findById(@PathVariable Long id){ 			
		Cliente obj = clienteservice.findById(id);				
		return ResponseEntity.ok().body(obj);
	}

}

package com.mcm.sp.services;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcm.sp.entities.Cliente;
import com.mcm.sp.repositories.ClienteRepository;

@Service
public class ClienteService {
		
	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente findById (Long id) {
		return clienteRepository.findById(id).orElseThrow(
				() -> new EntityNotFoundException("Cliente não encontrado " + id));         
	} 

}

package com.mcm.sp.services;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcm.sp.entities.Pedido;
import com.mcm.sp.repositories.PedidoRepository;

@Service
public class PedidoService {
		
	@Autowired
	private PedidoRepository pedidoRepository;
	
	public Pedido findById (Long id) {
		return pedidoRepository.findById(id).orElseThrow(
				() -> new EntityNotFoundException("Pedido não encontrado " + id));         
	} 

}

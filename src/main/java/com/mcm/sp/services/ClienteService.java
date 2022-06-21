package com.mcm.sp.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.mcm.sp.dto.ClienteDTO;
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
	
	public List <Cliente> findAll () {
		List <Cliente> clientes = clienteRepository.findAll();
		return clientes;
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return clienteRepository.findAll(pageRequest);
	}
	
	public Cliente insert(Cliente cliente) {
		cliente.setId(null);
		return clienteRepository.save(cliente);
	} 
	
	public Cliente update(Cliente cliente) {
		Cliente clienteExiste = findById(cliente.getId());
		updateCliente(clienteExiste, cliente);
		return clienteRepository.save(clienteExiste);		
	}
	
	private void updateCliente(Cliente clienteExiste, Cliente cliente) {
		clienteExiste.setNome(cliente.getNome());
		clienteExiste.setEmail(cliente.getEmail());
		
	}

	public void delete(Long id) {
		findById(id);
		try {
			clienteRepository.deleteById(id);
		}
		catch (Exception  e) {
			throw new DataIntegrityViolationException("Não é possível excluir, porque há entidade relacionas");
		}
	}
	
	public Cliente fromDTO(@Valid ClienteDTO clienteDTO) {
		return new Cliente(clienteDTO.getId(), clienteDTO.getNome(),clienteDTO.getEmail(), null, null);

	}
}

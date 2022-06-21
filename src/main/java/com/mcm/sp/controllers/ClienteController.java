package com.mcm.sp.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mcm.sp.dto.ClienteDTO;
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
	
	@GetMapping(value = "/")
	public ResponseEntity<List<ClienteDTO>> findAll(){ 			
		List <Cliente> clientes = clienteservice.findAll();
		List<ClienteDTO> clientesDTO = clientes.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(clientesDTO);
	}
	
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<ClienteDTO>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy,
			@RequestParam(value="direction", defaultValue="ASC") String direction){
		Page <Cliente> clientes = clienteservice.findPage(page, linesPerPage, orderBy, direction);
		Page <ClienteDTO> clientesDTO = clientes.map(obj -> new ClienteDTO(obj));
		return ResponseEntity.ok().body(clientesDTO);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public ResponseEntity<Cliente> insert (@Valid @RequestBody ClienteDTO clienteDTO){
		Cliente cliente = clienteservice.fromDTO(clienteDTO);
		cliente = clienteservice.insert(cliente);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(cliente.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Cliente> update(@Valid @RequestBody ClienteDTO clienteDTO, @PathVariable Long id){
		Cliente cliente = clienteservice.fromDTO(clienteDTO);
		cliente.setId(id);
		cliente = clienteservice.update(cliente);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void Delete(@PathVariable Long id){ 
		clienteservice.delete(id);
	}

}

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
import com.mcm.sp.dto.ClienteNewDTO;
import com.mcm.sp.entities.Cidade;
import com.mcm.sp.entities.Cliente;
import com.mcm.sp.entities.Endereco;
import com.mcm.sp.entities.enums.TipoCliente;
import com.mcm.sp.repositories.ClienteRepository;
import com.mcm.sp.repositories.EnderecoRepository;

@Service
public class ClienteService {
		
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;

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
		cliente = clienteRepository.save(cliente);
		enderecoRepository.saveAll(cliente.getEnderecos());
		return cliente;
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
	
	public Cliente fromDTO(ClienteNewDTO clienteDTO) {
		Cliente cli = new Cliente(null, clienteDTO.getNome(), clienteDTO.getEmail(), clienteDTO.getCpfOuCnpj(), TipoCliente.toEnum(clienteDTO.getTipo()));
		Cidade cid = new Cidade(clienteDTO.getCidadeId(), null, null);
		Endereco end = new Endereco(null, clienteDTO.getLogradouro(), clienteDTO.getNumero(), clienteDTO.getComplemento(), clienteDTO.getBairro(), clienteDTO.getCep(),cli, cid);
				
		cli.getEnderecos().add(end);
		cli.getTelefones().add(clienteDTO.getTelefone1());
		if (clienteDTO.getTelefone2()!=null) {
			cli.getTelefones().add(clienteDTO.getTelefone2());
		}
		if (clienteDTO.getTelefone3()!=null) {
			cli.getTelefones().add(clienteDTO.getTelefone3());
		}
		return cli;
	}
}

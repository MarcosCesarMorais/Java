package com.mcm.sp.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.mcm.sp.entities.Categoria;
import com.mcm.sp.entities.Produto;
import com.mcm.sp.repositories.CategoriaRepository;
import com.mcm.sp.repositories.ProdutoRepository;

@Service
public class ProdutoService {
		
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Produto findById (Integer id) {
		return produtoRepository.findById(id).orElseThrow(
				() -> new EntityNotFoundException("Produto não encontrado! Id:" + id));         
	} 
	
	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = categoriaRepository.findAllById(ids);
		return produtoRepository.search(nome, categorias, pageRequest);
		
	}

}

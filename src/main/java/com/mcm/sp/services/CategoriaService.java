package com.mcm.sp.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcm.sp.entities.Categoria;
import com.mcm.sp.repositories.CategoriaRepository;

@Service
public class CategoriaService {
		
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public List<Categoria> findAll () {
		List <Categoria> categorias = categoriaRepository.findAll();
		return categorias;
	}
	
	public Categoria findById (Long id) {
		return categoriaRepository.findById(id).orElseThrow(
				() -> new EntityNotFoundException("Categoria não encontrada " + id));         
	}

	public Categoria insert(Categoria categoria) {
		categoria.setId(null);
		return categoriaRepository.save(categoria);
	} 
	
	public Categoria update(Categoria categoria) {
		findById(categoria.getId());
		return categoriaRepository.save(categoria);
		
	}

}

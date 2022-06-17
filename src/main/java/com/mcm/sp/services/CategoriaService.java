package com.mcm.sp.services;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcm.sp.entities.Categoria;
import com.mcm.sp.repositories.CategoriaRepository;

@Service
public class CategoriaService {
		
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria findById (Long id) {
		return categoriaRepository.findById(id).orElseThrow(
				() -> new EntityNotFoundException("Categoria não encontrada " + id));         
	}

	public Categoria insert(Categoria categoria) {
		return categoriaRepository.save(categoria);
	} 

}

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

import com.mcm.sp.dto.CategoriaDTO;
import com.mcm.sp.entities.Categoria;
import com.mcm.sp.repositories.CategoriaRepository;

@Service
public class CategoriaService {
		
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public List <Categoria> findAll () {
		List <Categoria> categorias = categoriaRepository.findAll();
		return categorias;
	}
	
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return categoriaRepository.findAll(pageRequest);
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
	
	public void delete(Long id) {
		findById(id);
		try {
			categoriaRepository.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Não é possivel excluir uma categoria que possui produtos cadastrados");
		}
	}
	
	public Categoria fromDTO(@Valid CategoriaDTO categoriaDTO) {
		return new Categoria (categoriaDTO.getId(), categoriaDTO.getNome());
	}

	
}

package com.mcm.sp.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.mcm.sp.entities.Categoria;

public class CategoriaDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotEmpty(message="Preenchimento obrigatório!!")
	@Length(min=5, max=80, message="O tamanho deve ser entre 5 e 80 caracteres!!")
	private String nome;
	
	public CategoriaDTO() {}
	
	public CategoriaDTO (Categoria categoria) {
		id = categoria.getId();
		nome = categoria.getNome();
	}
	
	public CategoriaDTO(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}	
}

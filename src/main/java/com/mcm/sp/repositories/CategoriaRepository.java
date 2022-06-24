package com.mcm.sp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mcm.sp.entities.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository <Categoria, Integer>{

	

}

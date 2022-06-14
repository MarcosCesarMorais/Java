package com.mcm.sp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mcm.sp.entities.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository <Endereco, Long>{

}

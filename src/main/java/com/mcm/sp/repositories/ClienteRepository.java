package com.mcm.sp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mcm.sp.entities.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository <Cliente, Long>{

}

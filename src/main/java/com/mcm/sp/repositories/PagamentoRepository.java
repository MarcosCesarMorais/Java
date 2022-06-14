package com.mcm.sp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mcm.sp.entities.Pagamento;

@Repository
public interface PagamentoRepository extends JpaRepository <Pagamento, Long>{

}

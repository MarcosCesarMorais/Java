package com.mcm.sp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mcm.sp.entities.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository <Pedido, Long>{

}

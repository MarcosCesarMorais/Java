package com.mcm.sp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mcm.sp.entities.ItemPedido;

@Repository
public interface ItemPedidoRepository extends JpaRepository <ItemPedido, Long>{

}

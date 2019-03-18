package com.j2system.farmaciaonline.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.j2system.farmaciaonline.entities.Estado;

public interface EstadoRepository extends JpaRepository<Estado, Integer>{

	@Transactional(readOnly=true)
	public List<Estado> findAllByOrderByNome();

}

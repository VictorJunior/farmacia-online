package com.j2system.farmaciaonline.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.j2system.farmaciaonline.entities.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {
	
	@Transactional(readOnly = true) //informa que é só consulta e não precisa de transação, ajuda na performance
	Empresa findByCnpj(String cnpj);

	@Transactional(readOnly = true)
	List<Empresa> findByRazaoSocialContainingOrderByRazaoSocial(String razaoSocial);
	
	@Transactional(readOnly = true)
	List<Empresa> findByNomeFantasiaContainingOrderByNomeFantasia(String nomeFantasia);
	
}

package com.j2system.farmaciaonline.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.j2system.farmaciaonline.entities.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
	
	@Transactional(readOnly = true) //informa que é só uma consulta e não precisa de transação, ajuda na performance
	Empresa findByCnpj(String cnpj);

	@Transactional(readOnly = true)
	Empresa findByRazaoSocialContainingOrderByRazaoSocial(String razaoSocial);
	
}

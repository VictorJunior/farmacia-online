package com.j2system.farmaciaonline.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.j2system.farmaciaonline.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

	@Transactional(readOnly = true)
	@Query("SELECT obj FROM Produto obj WHERE obj.empresa.id = :empresaId ORDER BY obj.nome")
	Page<Produto> findProdutosPorEmpresa(@Param("empresaId") Integer empresa_id, 
							             Pageable pageRequest);

	@Transactional(readOnly = true)
	@Query("SELECT obj FROM Produto obj WHERE obj.empresa.id = :empresaId AND obj.nome LIKE %:nome% ORDER BY obj.nome")
	Page<Produto> findProdutosPorEmpresaNome(@Param("empresaId") Integer empresa_id, 
											 @Param("nome") String nome,
											 Pageable pageRequest);

}

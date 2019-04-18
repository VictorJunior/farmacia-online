package com.j2system.farmaciaonline.services.impl;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.j2system.farmaciaonline.entities.Cidade;
import com.j2system.farmaciaonline.entities.Empresa;
import com.j2system.farmaciaonline.entities.Estado;
import com.j2system.farmaciaonline.entities.Produto;
import com.j2system.farmaciaonline.repositories.CidadeRepository;
import com.j2system.farmaciaonline.repositories.EmpresaRepository;
import com.j2system.farmaciaonline.repositories.EstadoRepository;
import com.j2system.farmaciaonline.repositories.ProdutoRepository;
import com.j2system.farmaciaonline.services.DBService;

@Service
public class DBServiceImpl implements DBService {

	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public void instantiateTestDatabase() {
		Estado estado1 = new Estado(1,"Minas Gerais");
		Estado estado2 = new Estado(2,"Rio de Janeiro");
		
		estadoRepository.saveAll(Arrays.asList(estado1, estado2));
		
		Cidade cidade1 = new Cidade(1, "Formiga", estado1);
		Cidade cidade2 = new Cidade(2, "Niterói", estado2);
		Cidade cidade3 = new Cidade(3, "Rio de Janeiro", estado2);
		
		cidadeRepository.saveAll(Arrays.asList(cidade1, cidade2, cidade3));
		
		Empresa emp1 = new Empresa(null, "J2 Sistemas de Informação Ltda.", "J2 System", "11033517000101", "ISENTO", 
				                   "Rua Carmelita de Castro", "282", null, "Vila Carmelita", "35570-000", null, null, cidade1);
		Empresa emp2 = new Empresa(null, "Teste Ltda.", "Teste", "91610967000140", "ISENTO", 
                "Rua Carmelita de Castro", "282", null, "Vila Carmelita", "35570-000", null, null, cidade2);
		
		empresaRepository.saveAll(Arrays.asList(emp1, emp2));	
		
		/*Produto produto1 = new Produto(null, "1234", "Paracetamol", 10.99, null, null, null, null, null, null, null, emp1);
		Produto produto2 = new Produto(null, "12345", "Navagina", 11.99, null, null, null, null, null, null, null, emp1);
		Produto produto3 = new Produto(null, "123456", "Buscopan", 12.99, null, null, null, null, null, null, null, emp1);
		Produto produto4 = new Produto(null, "1234", "Paracetamol 2", 10.99, null, null, null, null, null, null, null, emp2);
		Produto produto5 = new Produto(null, "12345", "Navagina 2", 11.99, null, null, null, null, null, null, null, emp2);
		Produto produto6 = new Produto(null, "123456", "Buscopan 2", 12.99, null, null, null, null, null, null, null, emp2);
		
		produtoRepository.saveAll(Arrays.asList(produto1, produto2, produto3, produto4, produto5, produto6));*/
	}
	
}

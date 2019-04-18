package com.j2system.farmaciaonline.services.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.j2system.farmaciaonline.entities.Empresa;
import com.j2system.farmaciaonline.repositories.EmpresaRepository;
import com.j2system.farmaciaonline.services.EmpresaService;

@Service
public class EmpresaServiceImpl implements EmpresaService {

	private static final Logger log = LoggerFactory.getLogger(EmpresaServiceImpl.class);

	@Autowired
	private EmpresaRepository empresaRepository;

	@Override
	public Optional<Empresa> buscarPorCnpj(String cnpj) {
		log.info("Buscando uma empresa para o CNPJ {}", cnpj);
		return Optional.ofNullable(empresaRepository.findByCnpj(cnpj));
	}

	@Override
	public List<Empresa> buscarPorRazaoSocial(String razaoSocial) {
		log.info("Buscando empresas pela Razão Social {}", razaoSocial);
		return empresaRepository.findByRazaoSocialContainingOrderByRazaoSocial(razaoSocial);
	}

	@Override
	public List<Empresa> buscarPorNomeFantasia(String nomeFantasia) {
		log.info("Buscando empresas pelo Nome Fantasia {}", nomeFantasia);
		return empresaRepository.findByNomeFantasiaContainingOrderByNomeFantasia(nomeFantasia);
	}
	
	@Override
	public List<Empresa> buscarTodasEmpresas() {
		log.info("Buscando todas as empreasas");
		return empresaRepository.findAllByOrderByNomeFantasia();
	}	
	
	@Override
	public Optional<Empresa> buscarPorId(Integer id) {
		log.info("Buscando uma empresa pelo ID {}", id);
		return empresaRepository.findById(id);
	}
	
	@Override
	public Empresa persistir(Empresa empresa) {
		log.info("Persistindo empresa: {}", empresa);
		return this.empresaRepository.save(empresa);
	}

	@Override
	public void remover(Integer id) {
		log.info("Removendo a empresa ID {}", id);
		this.empresaRepository.deleteById(id);
	}

}

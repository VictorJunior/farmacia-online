package com.j2system.farmaciaonline.dtos;

import java.util.Optional;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CNPJ;

public class EmpresaDto {

	private Optional<Integer> id = Optional.empty();
	
	@NotEmpty(message = "Razão Social não pode ser vazio.")
	@Length(min = 3, max = 200, message = "Razão Social deve conter entre 3 e 200 caracteres.")
	private String razaoSocial;
	
	@NotEmpty(message = "Nome Fantasia não pode ser vazio.")
	@Length(min = 3, max = 200, message = "Nome Fantasia deve conter entre 3 e 200 caracteres.")	
	private String nomeFantasia;
	
	@NotEmpty(message = "CNPJ não pode ser vazio.")
	@CNPJ(message="CNPJ inválido")
	private String cnpj;
	
	private Optional<String> inscricaoEstadual = Optional.empty();
	
	@NotEmpty(message = "Endereço não pode ser vazio.")
	@Length(min = 3, max = 200, message = "Endereço deve conter entre 3 e 200 caracteres.")
	private String endereco;
	
	@NotEmpty(message = "Número não pode ser vazio.")
	@Length(min = 1, max = 30, message = "Número deve conter entre 1 e 30 caracteres.")
	private String numero;
	
	private Optional<String> complemento = Optional.empty();
	
	@NotEmpty(message = "Bairro não pode ser vazio.")
	@Length(min = 3, max = 200, message = "Bairro deve conter entre 1 e 30 caracteres.")
	private String bairro;
	
	@NotEmpty(message = "CEP não pode ser vazio.")
	private String cep;
	
	private Optional<Long> latitude = Optional.empty();	
	private Optional<Long> longitude = Optional.empty();	
	private Integer cidadeId;
	
	public EmpresaDto() {
	}

	public Optional<Integer> getId() {
		return id;
	}

	public void setId(Optional<Integer> id) {
		this.id = id;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Optional<String> getInscricaoEstadual() {
		return inscricaoEstadual;
	}

	public void setInscricaoEstadual(Optional<String> inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}

	public Optional<String> getComplemento() {
		return complemento;
	}

	public void setComplemento(Optional<String> complemento) {
		this.complemento = complemento;
	}

	public Optional<Long> getLatitude() {
		return latitude;
	}

	public void setLatitude(Optional<Long> latitude) {
		this.latitude = latitude;
	}

	public Optional<Long> getLongitude() {
		return longitude;
	}

	public void setLongitude(Optional<Long> longitude) {
		this.longitude = longitude;
	}

	public Integer getCidadeId() {
		return cidadeId;
	}

	public void setCidadeId(Integer cidadeId) {
		this.cidadeId = cidadeId;
	}

	@Override
	public String toString() {
		return "EmpresaDto [id=" + id + ", razaoSocial=" + razaoSocial + ", nomeFantasia=" + nomeFantasia + ", cnpj="
				+ cnpj + ", inscricaoEstadual=" + inscricaoEstadual + ", endereco=" + endereco + ", numero=" + numero
				+ ", complemento=" + complemento + ", bairro=" + bairro + ", cep=" + cep + ", latitude=" + latitude
				+ ", longitude=" + longitude + ", cidadeId=" + cidadeId + "]";
	}
	
}

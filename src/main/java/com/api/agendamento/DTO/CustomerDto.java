package com.api.agendamento.DTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import com.api.agendamento.anotations.Telefone;

public class CustomerDto {
  
	@NotBlank(message = "O campo nome não pode ser vazio")
	@Size(max=30, message = "O campo nome deve conter no máximo 30 caracteres")
	private String nome;
	
	@NotBlank(message = "O campo CPF não pode ser vazio")
	@CPF(message = "Este CPF informado é inválido")
	private String cpf;
	
	@NotBlank(message = "O campo telefone não pode ser vazio")	
	@Telefone
	private String telefone;
	
	

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	
}

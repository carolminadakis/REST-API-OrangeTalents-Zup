package br.com.zup.orangetalents.modelo;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
public class CadastroUsuario {

	@NotBlank(message = "Campo obrigatório!")
	@Id
	@CPF
	private String cpf;
	@NotBlank(message = "Campo obrigatório!")
	private String nome;
	
	@NotBlank(message = "Campo obrigatório!")
	@Email
	private String email;
	
	@JsonFormat(pattern="dd/mm/yyyy")
	private Date nascimento;
	
	@ManyToOne
	private CadastroAplicacaoVacina vacina;
	
	public CadastroUsuario() {
		
	}
	
	public CadastroUsuario(@NotBlank @CPF String cpf, @NotBlank String nome, @NotBlank @Email String email,
			@NotBlank Date nascimento) {
		this.cpf = cpf;
		this.nome = nome;
		this.email = email;
		this.nascimento = nascimento;
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CadastroUsuario other = (CadastroUsuario) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}

	//GETTTERS E SETTERS
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getNascimento() {
		return nascimento;
	}
	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}	
}

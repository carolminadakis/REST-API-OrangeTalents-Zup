package br.com.zup.orangetalents.modelo;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name= "cadastro_vacinacao")
public class CadastroAplicacaoVacina {

	//ATRIBUTOS E ANOTAÇÕES
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message = "Campo obrigatório!")
	private String vacina;
	@JsonFormat(pattern="dd/mm/yyyy")
	private Date vacinacao;
	@Email
	@NotBlank(message = "Campo obrigatório!")
	private String email;
	
	//CHAVE ESTRANGEIRA
	@OneToMany
	@JoinColumn(name = "usuario_cpf")
	private List<CadastroUsuario> usuario;
	
	//HASHCODE
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((vacina == null) ? 0 : vacina.hashCode());
		return result;
	}
	//EQUALS
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CadastroAplicacaoVacina other = (CadastroAplicacaoVacina) obj;
		if (vacina == null) {
			if (other.vacina != null)
				return false;
		} else if (!vacina.equals(other.vacina))
			return false;
		return true;
	}
	//GETTERS E SETTERS
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getVacina() {
		return vacina;
	}
	public void setVacina(String vacina) {
		this.vacina = vacina;
	}
	public Date getVacinacao() {
		return vacinacao;
	}
	public void setVacinacao(Date vacinacao) {
		this.vacinacao = vacinacao;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<CadastroUsuario> getUsuario() {
		return usuario;
	}
	public void setUsuario(List<CadastroUsuario> usuario) {
		this.usuario = usuario;
	}
}

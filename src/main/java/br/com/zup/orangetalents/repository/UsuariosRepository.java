package br.com.zup.orangetalents.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zup.orangetalents.modelo.CadastroUsuario;


public interface UsuariosRepository extends JpaRepository<CadastroUsuario, String> {
	
}

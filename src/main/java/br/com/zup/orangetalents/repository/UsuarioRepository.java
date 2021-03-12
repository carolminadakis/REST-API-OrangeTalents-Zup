package br.com.zup.orangetalents.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zup.orangetalents.modelo.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	Usuario findByCpfOrEmail(Long cpf, String email);
	Usuario findByEmail(String email);
}

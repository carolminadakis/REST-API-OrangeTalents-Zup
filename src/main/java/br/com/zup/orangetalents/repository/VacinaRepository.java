package br.com.zup.orangetalents.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zup.orangetalents.modelo.CadastroAplicacaoVacina;


public interface VacinaRepository extends JpaRepository<CadastroAplicacaoVacina, Long>{

}

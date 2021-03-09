package br.com.zup.orangetalents.controllers;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.orangetalents.modelo.CadastroAplicacaoVacina;
import br.com.zup.orangetalents.repository.VacinaRepository;

@RestController
@RequestMapping("/cadastrovacinacao")
public class VacinacaoController {
	
	@Autowired
	VacinaRepository vr;
	
	//EXIBE LISTAGEM DE CADASTRAMENTO DE VACINAÇÃO
	@GetMapping
	public List<CadastroAplicacaoVacina> lista() {
		return vr.findAll();		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CadastroAplicacaoVacina> buscaPorVacina(@PathVariable @RequestBody CadastroAplicacaoVacina id) {
		if(id == null) {
		return ResponseEntity.badRequest().build();
		} return ResponseEntity.ok(id);
	}
	
	//SALVA O CADASTRAMENTO DE VACINAÇÃO
	@PostMapping
	@Transactional
	public ResponseEntity<CadastroAplicacaoVacina> salva(@Valid @RequestBody CadastroAplicacaoVacina registroVacinacao) {
		if(registroVacinacao == null) {
			return ResponseEntity.badRequest().build();
			
		} vr.save(registroVacinacao);
		return ResponseEntity.status(HttpStatus.CREATED).body(registroVacinacao);
	}
	
	//ALTERA O CADASTRO DE VACINAÇÃO
	@PutMapping
	@Transactional
	public ResponseEntity<CadastroAplicacaoVacina> atualizaVacinacao(@Valid @RequestBody CadastroAplicacaoVacina atualizaVacinacao) {
		if(atualizaVacinacao == null) {
			return ResponseEntity.badRequest().build();
			
		} vr.save(atualizaVacinacao);
		return ResponseEntity.status(HttpStatus.OK).body(atualizaVacinacao);
	}
}

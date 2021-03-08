package br.com.zup.orangetalents.controllers;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.zup.orangetalents.modelo.CadastroUsuario;
import br.com.zup.orangetalents.repository.UsuariosRepository;

@RestController
@RequestMapping("/cadastrousuarios")
public class UsuarioController {
	
	@Autowired
	UsuariosRepository ur;
	
	//LISTA TODOS OS USUÁRIOS CADASTRADOS
	@GetMapping
	public List<CadastroUsuario> lista() {
		return ur.findAll();	
		
	}
	
	//BUSCA USUÁRIO POR CPF NO BANCO DE DADOS
	@GetMapping("/{cpf}")
	public CadastroUsuario buscaPorCpf(@PathVariable @Valid @RequestBody String cpf) {
		return ur.findById(cpf).orElseThrow(() -> new ResponseStatusException(NOT_FOUND,
												"Usuário não encontrado"));			
	}
	
	//FAZ A VALIDAÇÃO DAS INFORMAÇÕES E O CADASTRAMENTO NO BANCO DE DADOS
	@PostMapping			
	@Transactional
	public ResponseEntity<CadastroUsuario> cadastro(@Valid @RequestBody CadastroUsuario usuario) {
		if(usuario == null) {
			return ResponseEntity.badRequest().build();
		} ur.save(usuario);
		return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
	}
	
	//CRIAR MÉTODO PUT
	
	
	//CRIAR MÉTODO DELETE
}

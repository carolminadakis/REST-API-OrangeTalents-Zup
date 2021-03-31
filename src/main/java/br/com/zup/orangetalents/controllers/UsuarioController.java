package br.com.zup.orangetalents.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.orangetalents.modelo.Usuario;
import br.com.zup.orangetalents.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	UsuarioService usuarioService;
	
	//LISTA TODOS OS USUÁRIOS CADASTRADOS
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Usuario> lista() {
		return usuarioService.buscarTodos();	
		
	}
	
	//BUSCA USUÁRIO POR CPF NO BANCO DE DADOS
	@GetMapping("/{cpf}")
	@ResponseStatus(HttpStatus.OK)
	public Usuario buscaPorCpf(@PathVariable @Valid @RequestBody Long cpf) {
		return usuarioService.buscarPorCpf(cpf);		
	}
	
	//FAZ A VALIDAÇÃO DAS INFORMAÇÕES E O CADASTRAMENTO NO BANCO DE DADOS
	@PostMapping			
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario salvaCadastro(@Valid @RequestBody Usuario usuario) {
		return usuarioService.salvar(usuario);
	}
	
	//ALTERA AS INFORMAÇÕES DE CADASTRO EXISTENTE
	@PutMapping
	@ResponseStatus(HttpStatus.OK)
	public Usuario atualizaCadastro(@PathVariable Long cpf, @Valid @RequestBody Usuario usuario) {
		
		Usuario usuarioAtual = usuarioService.buscarPorCpf(cpf);
		BeanUtils.copyProperties(usuario, usuarioAtual, "cpf");
		return usuarioService.salvar(usuarioAtual);
	}
	
	
	//DELETA CADASTRO DE USUÁRIO E RETORNA NO CONTENT COMO CONFIRMAÇÃO
	@DeleteMapping("/{cpf}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletaCadastro(@PathVariable Long cpf) {
		usuarioService.deletar(cpf);
	}
}

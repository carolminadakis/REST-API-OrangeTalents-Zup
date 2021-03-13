package br.com.zup.orangetalents.controllers;

import java.util.List;
import javax.validation.Valid;
import br.com.zup.orangetalents.service.VacinacaoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import br.com.zup.orangetalents.modelo.Vacinacao;

@RestController
@RequestMapping("/vacinacoes")
public class VacinacaoController {
	
	@Autowired
	VacinacaoService vacinacaoService;
	
	//EXIBE LISTAGEM DE CADASTRAMENTO DE VACINAÇÃO
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Vacinacao> listar() {
		return vacinacaoService.buscarTodas();
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Vacinacao buscarPorVacinacao(@PathVariable Long id) {
		return vacinacaoService.buscarPorId(id);
	}
	
	//SALVA O CADASTRAMENTO DE VACINAÇÃO
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Vacinacao salvar(@Valid @RequestBody Vacinacao vacinacao) {
		return vacinacaoService.salvar(vacinacao);
	}
	
	//ALTERA O CADASTRO DE VACINAÇÃO
	@PutMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public Vacinacao atualizar(@PathVariable Long id, @Valid @RequestBody Vacinacao vacinacao) {

		Vacinacao vacinacaoAtual = vacinacaoService.buscarPorId(id);
		BeanUtils.copyProperties(vacinacao, vacinacaoAtual, "id");
		return vacinacaoService.salvar(vacinacaoAtual);
	}
}

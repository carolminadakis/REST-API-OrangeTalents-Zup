package br.com.zup.orangetalents.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.zup.orangetalents.exception.EntidadeEmUsoException;
import br.com.zup.orangetalents.exception.EntidadeDuplicada;
import br.com.zup.orangetalents.exception.EntidadeNaoEncontradaException;
import br.com.zup.orangetalents.modelo.Usuario;
import br.com.zup.orangetalents.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	private static final String MSG_USUARIO_EM_USO 
	= "Não existe um cadastro de usuario com cpf %d";
	
	private static final String MSG_USUARIO_NAO_ENCONTRADA 
	= "Usuario de cpf %d não pode ser removido, pois está em uso";
	
	private static final String MSG_USUARIO_JA_EXISTE 
	= "Já existe um usuário na base com cpf %d e/ou email %s";
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	public List<Usuario> buscarTodos(){
		return usuarioRepository.findAll();
	}
	
	public Usuario buscarPorCpf(Long cpf) {
		return usuarioRepository.findById(cpf).orElseThrow(() -> new EntidadeNaoEncontradaException(
				String.format(MSG_USUARIO_NAO_ENCONTRADA, cpf)));
	}
	
	public Usuario salvar(Usuario usuario) {
		validaSeRegistroDuplicado(usuario);
		
		return usuarioRepository.save(usuario);
	}
	
	public void deletar(Long cpf) {
		try {
			usuarioRepository.deleteById(cpf);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
				String.format(MSG_USUARIO_EM_USO, cpf));
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
				String.format(MSG_USUARIO_NAO_ENCONTRADA, cpf));
		}
	}
	
	/*
	 * Email e Cpf devem ser únicos
	 */
	private void validaSeRegistroDuplicado(Usuario usuario) {
		Usuario usuarioBanco = usuarioRepository.findByCpfOrEmail(usuario.getCpf(), usuario.getEmail());

		if ((usuarioBanco != null) && (usuario.getEmail() != usuarioBanco.getEmail() || usuario.getCpf() != usuarioBanco.getCpf())) {
			throw new EntidadeDuplicada(
					String.format(MSG_USUARIO_JA_EXISTE, usuario.getCpf(), usuario.getEmail()));
		}		
	}

}

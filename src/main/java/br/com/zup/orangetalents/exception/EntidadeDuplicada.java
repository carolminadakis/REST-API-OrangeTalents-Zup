package br.com.zup.orangetalents.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EntidadeDuplicada extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EntidadeDuplicada(String mensagem) {
		super(mensagem);
	}
	
}

package br.com.zup.orangetalents.service;

import br.com.zup.orangetalents.exception.EntidadeEmUsoException;
import br.com.zup.orangetalents.exception.EntidadeNaoEncontradaException;
import br.com.zup.orangetalents.modelo.Vacinacao;
import br.com.zup.orangetalents.repository.VacinacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VacinacaoService {

    private static final String MSG_VACINACAO_EM_USO
            = "Não existe um cadastro de vacinação com id %d";

    private static final String MSG_VACINACAO_NAO_ENCONTRADA
            = "Vacinação com id %d não pode ser removida, pois está em uso";

    @Autowired
    VacinacaoRepository vacinacaoRepository;

    public List<Vacinacao> buscarTodas(){

        return vacinacaoRepository.findAll();
    }

    public Vacinacao buscarPorId(Long id) {
        return vacinacaoRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException(
                String.format(MSG_VACINACAO_NAO_ENCONTRADA, id)));
    }

    public Vacinacao salvar(Vacinacao vacinacao) {
        return vacinacaoRepository.save(vacinacao);
    }

    public void deletar(Long id) {
        try {
            vacinacaoRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(
                    String.format(MSG_VACINACAO_EM_USO, id));
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format(MSG_VACINACAO_NAO_ENCONTRADA, id));
        }
    }
}

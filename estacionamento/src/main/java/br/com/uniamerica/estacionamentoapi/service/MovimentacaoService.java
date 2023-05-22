package br.com.uniamerica.estacionamentoapi.service;

import br.com.uniamerica.estacionamentoapi.entity.Movimentação;
import br.com.uniamerica.estacionamentoapi.repository.MovimentacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MovimentacaoService {

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    public void atualizaMovimentacao(Movimentação movimentacao){
        final Movimentação movimentacaoBancoDeDados = this.movimentacaoRepository.findById(movimentacao.getId()).orElse(null);
        movimentacao.setCadastro(movimentacaoBancoDeDados.getCadastro());

        this.movimentacaoRepository.save(movimentacao);
    }
}


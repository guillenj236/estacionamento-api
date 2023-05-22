package br.com.uniamerica.estacionamentoapi.service;

import br.com.uniamerica.estacionamentoapi.entity.Veiculo;
import br.com.uniamerica.estacionamentoapi.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;
    @Transactional(rollbackFor = Exception.class)
    public void validaVeiculo (Veiculo veiculo)
    {
        Assert.isTrue(veiculo.getPlaca().length() > 15, "Placa inválida.");
        Assert.isTrue(veiculo.getPlaca().equals(""), "Placa não pode ser nula");

        this.veiculoRepository.save(veiculo);
    }
    public void atualizaVeiculo (Veiculo veiculo){
        final Veiculo veiculoBancoDeDados = this.veiculoRepository.findById(veiculo.getId()).orElse(null);
        veiculo.setCadastro(veiculoBancoDeDados.getCadastro());

        this.veiculoRepository.save(veiculo);
    }
}


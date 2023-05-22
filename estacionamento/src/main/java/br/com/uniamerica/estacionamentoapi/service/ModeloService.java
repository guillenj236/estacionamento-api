package br.com.uniamerica.estacionamentoapi.service;

import br.com.uniamerica.estacionamentoapi.entity.Modelo;
import br.com.uniamerica.estacionamentoapi.repository.ModeloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
public class ModeloService {
    @Autowired
    private ModeloRepository modeloRepository;

    @Transactional(rollbackFor = Exception.class)
    public void validaModelo(Modelo modelo) {
        Assert.isTrue(modelo.getNome().equals(""), "Marca não pode ser nulo");
        Assert.isTrue(modelo.getNome().length() > 50, "Marca maior que 50 caracteres");


        this.modeloRepository.save(modelo);
    }
    public void atualizaModelo (Modelo modelo){
        final Modelo modeloBancoDeDados = this.modeloRepository.findById(modelo.getId()).orElse(null);
        modelo.setCadastro(modeloBancoDeDados.getCadastro());

        this.modeloRepository.save(modelo);
    }
}


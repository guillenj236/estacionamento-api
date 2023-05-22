package br.com.uniamerica.estacionamentoapi.service;

import br.com.uniamerica.estacionamentoapi.entity.Marca;
import br.com.uniamerica.estacionamentoapi.repository.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
public class MarcaService {

    @Autowired
    private MarcaRepository marcaRepository;

    @Transactional(rollbackFor = Exception.class)
    public void validaMarca(Marca marca) {
        Assert.isTrue(!marca.getNome().equals(""), "Marca não pode ser nulo");
        Assert.isTrue(marca.getNome().length() < 50, "Marca maior que 50 caracteres");

        this.marcaRepository.save(marca);
    }
    public void atualizaMarca (Marca marca){
        final Marca marcaBancoDeDados = this.marcaRepository.findById(marca.getId()).orElse(null);
        marca.setCadastro(marcaBancoDeDados.getCadastro());

        this.marcaRepository.save(marca);
    }
}

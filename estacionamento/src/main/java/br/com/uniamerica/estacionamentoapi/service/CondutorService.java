package br.com.uniamerica.estacionamentoapi.service;

import br.com.uniamerica.estacionamentoapi.configs.ValidaCPF;
import br.com.uniamerica.estacionamentoapi.entity.Condutor;
import br.com.uniamerica.estacionamentoapi.repository.CondutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
public class CondutorService {

    @Autowired
    public ValidaCPF validarCPF;
    @Autowired
    private CondutorRepository condutorRep;

    @Transactional(rollbackFor = Exception.class)
    public void validaCondutor (Condutor condutor)
    {
        Assert.isTrue(condutor.getNome().length() <= 50,"Nome maior do que 50 caracteres");

        Assert.isTrue(!condutor.getNome().equals(""), "Nome não pode ser nulo.");
        Assert.isTrue(!condutor.getCpf().equals(""), "CPF não pode ser nulo.");

        Assert.isTrue(!condutor.getTelefone().equals(""), "Telefone não pode ser nulo.");
        Assert.isTrue(condutor.getTelefone().length() <  17, "Tamanho de telefone inválido.");


        if (this.validarCPF.isCPF(condutor.getCpf()) == false) {
            throw new RuntimeException("Cpf inválido");
        }

        this.condutorRep.save(condutor);
    }
    public void atualizaCondutor (Condutor condutor){
        final Condutor condutorBancoDeDados = this.condutorRep.findById(condutor.getId()).orElse(null);
        condutor.setCadastro(condutorBancoDeDados.getCadastro());

        this.condutorRep.save(condutor);
    }
}

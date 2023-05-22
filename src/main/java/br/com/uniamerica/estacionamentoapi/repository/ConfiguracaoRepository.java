package br.com.uniamerica.estacionamentoapi.repository;

import br.com.uniamerica.estacionamentoapi.entity.Configuração;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfiguracaoRepository extends JpaRepository<Configuração,Long> {
}

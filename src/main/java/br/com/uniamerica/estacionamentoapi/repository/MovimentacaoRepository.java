package br.com.uniamerica.estacionamentoapi.repository;

import br.com.uniamerica.estacionamentoapi.entity.Movimentação;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovimentacaoRepository extends JpaRepository<Movimentação, Long> {
}

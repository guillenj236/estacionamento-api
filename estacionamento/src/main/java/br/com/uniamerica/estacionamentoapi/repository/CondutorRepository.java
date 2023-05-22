package br.com.uniamerica.estacionamentoapi.repository;

import br.com.uniamerica.estacionamentoapi.entity.Condutor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CondutorRepository extends JpaRepository<Condutor, Long> {

}

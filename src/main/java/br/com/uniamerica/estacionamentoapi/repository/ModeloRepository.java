package br.com.uniamerica.estacionamentoapi.repository;

import br.com.uniamerica.estacionamentoapi.entity.Modelo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModeloRepository extends JpaRepository<Modelo, Long> {
}

package br.com.uniamerica.estacionamentoapi.repository;

import br.com.uniamerica.estacionamentoapi.entity.Marca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarcaRepository extends JpaRepository<Marca,Long> {
}

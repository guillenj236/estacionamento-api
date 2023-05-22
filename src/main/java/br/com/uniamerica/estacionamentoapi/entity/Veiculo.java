package br.com.uniamerica.estacionamentoapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "veiculos", schema = "public")
public class Veiculo extends AbstractEntity{
    @Getter @Setter
    @NotNull
    @Column(name = "placa", nullable = false, unique = true, length = 10)
    private String placa;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "veiculos_id")
    private Modelo modelo;
    @Enumerated(EnumType.STRING)
    @Getter @Setter
    @Column(name = "cor", length = 20)
    @NotNull
    private Cor cor;
    @Enumerated(EnumType.STRING)
    @Getter @Setter
    @Column(name = "tipo_veiculo", length = 20)
    @NotNull
    private Tipo tipo;
    @Column(name = "ano")
    @NotNull
    private int ano;

    @Getter
    @OneToMany(mappedBy = "veiculo")
    private List<Movimentação> movimentacoes;

    public Veiculo() {
    }
}

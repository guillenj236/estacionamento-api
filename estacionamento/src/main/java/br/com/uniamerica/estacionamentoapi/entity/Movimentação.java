package br.com.uniamerica.estacionamentoapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
@Entity
@Table(name = "movimentações", schema = "public")
public class Movimentação extends AbstractEntity{
    @ManyToOne
    @JoinColumn(name = "veiculos_id", unique = true)
    @NotNull
    private Veiculo veiculo;

    @ManyToOne
    @JoinColumn(name = "movimentacoes_id")
    @NotNull
    private Condutor condutor;
    @Getter @Setter
    @Column(name = "entrada", nullable = false, length = 10)
    private LocalDateTime entrada;
    @Getter @Setter
    @Column(name = "saida", length = 10)
    private LocalDateTime saida;
    @Getter @Setter
    @Column(name = "tempo", length = 10)
    private LocalTime tempo;
    @Getter @Setter
    @Column(name = "tempoDesconto", length = 10)
    private LocalTime tempoDesconto;
    @Getter @Setter
    @Column(name = "tempoMulta", length = 10)
    private LocalTime tempoMulta;
    @Getter @Setter
    @Column(name = "valorDesconto", length = 10)
    private BigDecimal valorDesconto;
    @Getter @Setter
    @Column(name = "valorMulta", length = 10)
    private BigDecimal valorMulta;
    @Getter @Setter
    @Column(name = "valorHora", length = 10)
    private BigDecimal valorHora;
    @Getter @Setter
    @Column(name = "valorTotal", length = 10)
    private BigDecimal valorTotal;
    @Getter @Setter
    @Column(name = "valorHoraMulta", length = 10)
    private BigDecimal valorHoraMulta;

}

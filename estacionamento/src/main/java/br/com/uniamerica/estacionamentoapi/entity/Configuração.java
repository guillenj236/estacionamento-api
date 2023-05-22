package br.com.uniamerica.estacionamentoapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalTime;
@Entity
@Table(name = "configurações", schema = "public")
public class Configuração extends AbstractEntity{
    @Getter @Setter
    @Column(name = "valorHora", length = 10)
    private BigDecimal valorHora;
    @Getter@Setter
    @Column(name = "valorMinutoMulta", length = 10)
    private BigDecimal valorMinutoMulta;
    @Getter @Setter
    @Column(name = "inicioExpediente", length = 10)
    private LocalTime inicioExpediente;
    @Getter @Setter
    @Column(name = "fimExpediente", length = 10)
    private LocalTime fimExpediente;
    @Getter @Setter
    @Column(name = "tempoParaDesconto", length = 10)
    private LocalTime tempoParaDesconto;
    @Getter @Setter
    @Column(name = "tempoDeDesconto", length = 10)
    private LocalTime tempoDeDesconto;
    @Getter @Setter
    @Column(name = "gerarDesconto", length = 10)
    private boolean gerarDesconto;
    @Getter @Setter
    @Column(name = "vagasMoto", length = 10)
    private int vagasMoto;
    @Getter @Setter
    @Column(name = "vagasCarro", length = 10)
    private int vagasCarro;
    @Getter @Setter
    @Column(name = "vagasVan", length = 10)
    private int vagasVan;

    public Configuração(){
    }
}

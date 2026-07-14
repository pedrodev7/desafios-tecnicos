package com.project.picpaysimplificado.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "transacao")
@Table(name = "transacao")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "pagador_id")
    private Usuario pagador;

    @ManyToOne
    @JoinColumn(name = "beneficiario_id")
    private Usuario beneficiario;
    private BigDecimal valor;
    private LocalDateTime dataHoraTransacao;

    public Transacao(Usuario pagador, Usuario beneficiario, BigDecimal valor, LocalDateTime dataHoraTransacao) {
        this.pagador = pagador;
        this.beneficiario = beneficiario;
        this.valor = valor;
        this.dataHoraTransacao = dataHoraTransacao;
    }
}

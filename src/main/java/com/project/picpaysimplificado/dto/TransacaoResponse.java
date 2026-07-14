package com.project.picpaysimplificado.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransacaoResponse(String id, Long pagadorId, Long beneficiarioId, BigDecimal valor, LocalDateTime dataHoraTransacao) {
}

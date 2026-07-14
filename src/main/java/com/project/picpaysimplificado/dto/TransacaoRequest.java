package com.project.picpaysimplificado.dto;

import java.math.BigDecimal;

public record TransacaoRequest(BigDecimal valor, Long pagadorId, Long beneficiarioId) {
}

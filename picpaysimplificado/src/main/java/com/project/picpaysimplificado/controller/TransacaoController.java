package com.project.picpaysimplificado.controller;

import com.project.picpaysimplificado.dto.TransacaoRequest;
import com.project.picpaysimplificado.services.TransacaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {
    private final TransacaoService transacaoService;

    public TransacaoController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @PostMapping
    public ResponseEntity<?> realizarTransacao(@RequestBody TransacaoRequest transacaoRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(transacaoService.salvarTransacao(transacaoRequest));
    }
}

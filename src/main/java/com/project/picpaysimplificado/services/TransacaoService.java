package com.project.picpaysimplificado.services;

import com.project.picpaysimplificado.dto.TransacaoRequest;
import com.project.picpaysimplificado.dto.TransacaoResponse;
import com.project.picpaysimplificado.model.TipoUsuario;
import com.project.picpaysimplificado.model.Transacao;
import com.project.picpaysimplificado.model.Usuario;
import com.project.picpaysimplificado.repository.TransacaoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class TransacaoService {
    private final TransacaoRepository transacaoRepository;
    private final UsuarioService usuarioService;

    public TransacaoService(TransacaoRepository transacaoRepository, UsuarioService usuarioService) {
        this.transacaoRepository = transacaoRepository;
        this.usuarioService = usuarioService;
    }

    @Transactional
    public TransacaoResponse salvarTransacao(TransacaoRequest transacaoRequest) {
        Usuario pagador = usuarioService.buscarUsuario(transacaoRequest.pagadorId());
        Usuario beneficiario = usuarioService.buscarUsuario(transacaoRequest.beneficiarioId());

        validarTransacao(pagador, beneficiario, transacaoRequest);
        //TODO - Implementar a chamada do microserviço de autorização

        pagador.setValor(pagador.getValor().subtract(transacaoRequest.valor()));
        beneficiario.setValor(beneficiario.getValor().add(transacaoRequest.valor()));


        Transacao transacao = new Transacao(pagador, beneficiario, transacaoRequest.valor(), LocalDateTime.now());
        return new TransacaoResponse(transacaoRepository.save(transacao).getId(), pagador.getId(), beneficiario.getId(), transacaoRequest.valor(), transacao.getDataHoraTransacao());
    }

    private void validarTransacao(Usuario pagador, Usuario beneficiario, TransacaoRequest transacaoRequest) {
        if(pagador.getTipoUsuario().equals(TipoUsuario.LOJISTA)) {
            throw new RuntimeException("Lojistas não podem realizar transações.");
        }

        if(pagador.getValor().compareTo(transacaoRequest.valor()) < 0) {
            throw new RuntimeException("Saldo insuficiente para realizar a transação.");
        }
    }
}

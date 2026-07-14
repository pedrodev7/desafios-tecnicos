package com.project.picpaysimplificado.services;

import com.project.picpaysimplificado.dto.TransacaoRequest;
import com.project.picpaysimplificado.dto.TransacaoResponse;
import com.project.picpaysimplificado.exception.InsufficientBalanceException;
import com.project.picpaysimplificado.exception.UnauthorizedTransactionException;
import com.project.picpaysimplificado.model.*;
import com.project.picpaysimplificado.repository.TransacaoRepository;
import com.project.picpaysimplificado.services.notification.NotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class TransacaoService {
    private final TransacaoRepository transacaoRepository;
    private final UsuarioService usuarioService;
    private final NotificationService notificationService;
    private final RestTemplate restTemplate;

    public TransacaoService(TransacaoRepository transacaoRepository, UsuarioService usuarioService, NotificationService notificationService, RestTemplate restTemplate) {
        this.transacaoRepository = transacaoRepository;
        this.usuarioService = usuarioService;
        this.notificationService = notificationService;
        this.restTemplate = restTemplate;
    }

    @Transactional
    public TransacaoResponse salvarTransacao(TransacaoRequest transacaoRequest) {
        Usuario pagador = usuarioService.buscarUsuario(transacaoRequest.pagadorId());
        Usuario beneficiario = usuarioService.buscarUsuario(transacaoRequest.beneficiarioId());

        this.validarTransacao(pagador, beneficiario, transacaoRequest);
        if(!transacaoAutorizada()){
            throw new UnauthorizedTransactionException("Transação não autorizada");
        }


        pagador.setValor(pagador.getValor().subtract(transacaoRequest.valor()));
        beneficiario.setValor(beneficiario.getValor().add(transacaoRequest.valor()));

        this.enviarMensagem(beneficiario.getEmail());

        Transacao transacao = new Transacao(pagador, beneficiario, transacaoRequest.valor(), LocalDateTime.now());
        return new TransacaoResponse(transacaoRepository.save(transacao).getId(), pagador.getId(), beneficiario.getId(), transacaoRequest.valor(), transacao.getDataHoraTransacao());
    }

    private void validarTransacao(Usuario pagador, Usuario beneficiario, TransacaoRequest transacaoRequest) {
        if(pagador.getTipoUsuario().equals(TipoUsuario.LOJISTA)) {
            throw new UnauthorizedTransactionException("Lojistas não podem realizar transações.");
        }

        if(pagador.getValor().compareTo(transacaoRequest.valor()) < 0) {
            throw new InsufficientBalanceException("Saldo insuficiente para realizar a transação.");
        }
    }

    private Boolean transacaoAutorizada(){
        ResponseEntity<Map> autorizacaoResponse = restTemplate.getForEntity("http://localhost:8081/authorize", Map.class);

        if(autorizacaoResponse.getStatusCode() == HttpStatus.OK){
            String retorno = autorizacaoResponse.getBody().get("mensage").toString();
            return "Autorizado".equalsIgnoreCase(retorno);
        }
        return false;
    }

    private void enviarMensagem(String destinatario){
        notificationService.processarNotificacao(new Email("[EMAIL] Recebeu recebeu uma transferencia. Parabéns", destinatario, "Bank - Você recebeu Dinheiro!"));
        notificationService.processarNotificacao(new SMS("[SMS] Você recebeu uma Transferencia. Parabéns", destinatario));
    }
}

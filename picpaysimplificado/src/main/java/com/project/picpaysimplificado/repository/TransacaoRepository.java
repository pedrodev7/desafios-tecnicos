package com.project.picpaysimplificado.repository;


import com.project.picpaysimplificado.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepository  extends JpaRepository<Transacao, Long> {
}

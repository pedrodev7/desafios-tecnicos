package com.project.picpaysimplificado.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ResponseErro(String menssage, HttpStatus code, LocalDateTime localDateTime) {
}

package com.project.picpaysimplificado.dto;

import com.project.picpaysimplificado.model.TipoUsuario;

public record UsuarioRequest(String nome, String documento, String email, String senha, TipoUsuario tipoUsuario) {
}

package com.project.picpaysimplificado.dto;

import com.project.picpaysimplificado.model.TipoUsuario;

public record UsuarioResponse(Long id, String nome, TipoUsuario tipoUsuario) {
}

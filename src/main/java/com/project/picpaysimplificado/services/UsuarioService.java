package com.project.picpaysimplificado.services;

import com.project.picpaysimplificado.dto.UsuarioRequest;
import com.project.picpaysimplificado.dto.UsuarioResponse;
import com.project.picpaysimplificado.model.Usuario;
import com.project.picpaysimplificado.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioResponse salvarUsuario(UsuarioRequest usuarioRequest) {
        Usuario usuario = new Usuario(usuarioRequest);
        usuario.setValor(new BigDecimal(100));
        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        return new UsuarioResponse(usuarioSalvo.getId(), usuarioSalvo.getNome(), usuarioSalvo.getTipoUsuario());
    }

    public UsuarioResponse getUsuarioById(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return new UsuarioResponse(usuario.getId(), usuario.getNome(), usuario.getTipoUsuario());
    }

    public Usuario buscarUsuario(Long id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }
}

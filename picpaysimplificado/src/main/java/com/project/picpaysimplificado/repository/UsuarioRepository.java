package com.project.picpaysimplificado.repository;

import com.project.picpaysimplificado.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}

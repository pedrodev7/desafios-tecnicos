package com.project.picpaysimplificado.model;

import com.project.picpaysimplificado.dto.UsuarioRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity(name = "usuario")
@Table(name = "usuario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;

    @Column(unique = true)
    private String documento;

    @Column(unique = true)
    private String email;
    private String senha;

    @Enumerated(EnumType.STRING)
    private TipoUsuario tipoUsuario;

    private BigDecimal valor;

    public Usuario(UsuarioRequest usuarioRequest) {
        this.nome = usuarioRequest.nome();
        this.documento = usuarioRequest.documento();
        this.email = usuarioRequest.email();
        this.senha = usuarioRequest.senha();
        this.tipoUsuario = usuarioRequest.tipoUsuario();
    }
}

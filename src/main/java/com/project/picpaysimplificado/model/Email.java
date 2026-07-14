package com.project.picpaysimplificado.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Email extends Notification{
    private String assunto;

    public Email(String mensagem, String destinatario, String assunto) {
        super(mensagem, destinatario);
        this.assunto = assunto;
    }

}

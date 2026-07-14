package com.project.picpaysimplificado.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class SMS extends Notification{

    public SMS(String mensagem, String destinatario) {
        super(mensagem, destinatario);
    }
}

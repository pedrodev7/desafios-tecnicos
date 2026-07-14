package com.project.picpaysimplificado.services.notification;

import com.project.picpaysimplificado.model.Notification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {
    private final List<NotificationSender<? extends Notification>> senders;

    public NotificationService(List<NotificationSender<? extends Notification>> senders) {
        this.senders = senders;
    }

    public void processarNotificacao(Notification notification){
        NotificationSender envio = senders.stream()
                .filter(s -> s.suportaEnvio(notification))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Não foi encontrada a classe de Envio"));

        envio.enviar(notification);
    }
}

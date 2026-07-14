package com.project.picpaysimplificado.services.notification.envio;

import com.project.picpaysimplificado.model.Email;
import com.project.picpaysimplificado.model.Notification;
import com.project.picpaysimplificado.services.notification.NotificationSender;
import org.springframework.stereotype.Service;

@Service
public class EmailNotification implements NotificationSender<Email> {

    @Override
    public void enviar(Email notification) {
        System.out.println("Assunto: " + notification.getAssunto());
        System.out.println("Mensagem: " + notification.getMensagem());
    }

    @Override
    public boolean suportaEnvio(Notification notification) {
        return notification instanceof Email;
    }
}

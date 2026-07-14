package com.project.picpaysimplificado.services.notification.envio;

import com.project.picpaysimplificado.model.Notification;
import com.project.picpaysimplificado.model.SMS;
import com.project.picpaysimplificado.services.notification.NotificationSender;
import org.springframework.stereotype.Service;

@Service
public class SMSNotification implements NotificationSender<SMS> {


    @Override
    public void enviar(SMS notification) {
        System.out.println("Mensagem: " + notification.getMensagem());
    }

    @Override
    public boolean suportaEnvio(Notification notification) {
        return notification instanceof SMS;
    }
}

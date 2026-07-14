package com.project.picpaysimplificado.services.notification;

import com.project.picpaysimplificado.model.Notification;

public interface NotificationSender<T extends Notification> {
    void enviar(T notification);
    boolean suportaEnvio(Notification notification);
}

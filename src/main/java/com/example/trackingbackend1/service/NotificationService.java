package com.example.trackingbackend1.service;

import com.example.trackingbackend1.model.Notification;
import com.example.trackingbackend1.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    public Optional<Notification> getNotificationById(Long id) {
        return notificationRepository.findById(id);
    }

    public Notification saveNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    public Notification updateNotification(Long id, Notification notificationDetails) {
        Optional<Notification> existingNotification = notificationRepository.findById(id);
        if (existingNotification.isPresent()) {
            Notification notification = existingNotification.get();
            notification.setMessage(notificationDetails.getMessage());
            notification.setType(notificationDetails.getType());
            notification.setRecipient(notificationDetails.getRecipient());
            notification.setSender(notificationDetails.getSender());
            return notificationRepository.save(notification);
        } else {
            throw new RuntimeException("Notificación no encontrada");
        }
    }

    public void deleteNotification(Long id) {
        notificationRepository.deleteById(id);
    }
}

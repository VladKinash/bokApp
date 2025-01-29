package org.libapp.libapp.service;

import org.libapp.libapp.entity.Notification;
import org.libapp.libapp.entity.User;
import org.libapp.libapp.repository.NotificationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepo notificationRepo;

    public Notification createNotification(User user, String message) {
        Notification notification = new Notification();
        notification.setUser(user);
        notification.setMessage(message);
        notification.setCreatedAt(Instant.now());
        notification.setIsRead(false);
        return notificationRepo.save(notification);
    }

    public List<Notification> getAllNotificationsForUser(User user) {
        return notificationRepo.findByUserOrderByCreatedAtDesc(user);
    }

    public List<Notification> getUnreadNotificationsForUser(User user) {
        return notificationRepo.findByUserAndIsReadFalseOrderByCreatedAtDesc(user);
    }

    public long getUnreadCountForUser(User user) {
        return notificationRepo.countByUserAndIsReadFalse(user);
    }

    public void markAsRead(Integer notificationId) {
        Notification notification = notificationRepo.findById(notificationId).orElse(null);
        if (notification != null && Boolean.FALSE   .equals(notification.getIsRead())) {
            notification.setIsRead(true);
            notificationRepo.save(notification);
        }
    }
}

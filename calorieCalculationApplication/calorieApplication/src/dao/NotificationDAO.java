package dao;

import entity.*;
import java.util.List;

public interface NotificationDAO {
    public List<Notification> getNotificationForAUser(int userId);
    public List<Notification> getAllNotifications(); //mund te behet viewAllNotifications?
}

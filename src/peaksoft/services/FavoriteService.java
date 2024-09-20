package peaksoft.services;

import peaksoft.models.Announcement;
import peaksoft.models.User;

import java.util.List;
import java.util.Map;

public interface FavoriteService {

    void favoriteAnnouncement(Long userId, Long announcementId);

    Map<User, List<Announcement>> getAllByUserId(Long id);
}

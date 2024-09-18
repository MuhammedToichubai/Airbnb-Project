package peaksoft.services;

import peaksoft.models.Announcement;

public interface AnnouncementService {
    String save(Long userId, Announcement announcement);
}

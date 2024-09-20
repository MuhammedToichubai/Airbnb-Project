package peaksoft.services;

import peaksoft.models.Announcement;

import java.util.List;

public interface AnnouncementService {
    String save(Long userId, Announcement announcement);

    List<Announcement> getAllAnnouncementsByOwnerId(Long ownerId);

    List<Announcement> getAll();
}

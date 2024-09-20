package peaksoft.dao;

import peaksoft.models.Announcement;

import java.util.List;

public interface AnnouncementDao {
    void save(Announcement announcement);

    List<Announcement> getAnnouncementsByOwnerId(Long ownerId);

    List<Announcement> getAll();
}

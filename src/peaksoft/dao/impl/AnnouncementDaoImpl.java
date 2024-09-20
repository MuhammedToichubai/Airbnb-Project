package peaksoft.dao.impl;

import peaksoft.dao.AnnouncementDao;
import peaksoft.dao.CustomDao;
import peaksoft.databse.Database;
import peaksoft.exceptions.NotFoundException;
import peaksoft.models.Announcement;
import peaksoft.models.IdGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AnnouncementDaoImpl implements AnnouncementDao, CustomDao<Announcement, Long> {
    private final Database database;

    public AnnouncementDaoImpl(Database database) {
        this.database = database;
    }

    @Override
    public void save(Announcement announcement) {
        announcement.setId(IdGenerator.getAnnouncementId());
        database.announcements.add(announcement);
    }

    @Override
    public Announcement findById(Long id) {
        for (Announcement announcement : database.announcements) {
            if (announcement.getId().equals(id)) return announcement;
        }
        throw new NotFoundException("Announcement with id: " + id + " not found!");
    }

    @Override
    public List<Announcement> findAll() {
        return database.announcements;
    }

    @Override
    public List<Announcement> getAnnouncementsByOwnerId(Long ownerId) {
        List<Announcement> userAnnouncements = new ArrayList<>();  // empty
        for (Announcement announcement : database.announcements) {
            if (announcement.getOwner().getId().equals(ownerId)) {
                userAnnouncements.add(announcement);
            }
        }
        return userAnnouncements;
    }

    @Override
    public List<Announcement> getAll() {
        return database.announcements;
    }
}

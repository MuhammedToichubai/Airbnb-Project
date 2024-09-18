package peaksoft.dao.impl;

import peaksoft.dao.AnnouncementDao;
import peaksoft.databse.Database;
import peaksoft.models.Announcement;
import peaksoft.models.IdGenerator;

public class AnnouncementDaoImpl implements AnnouncementDao {
    private final Database database;

    public AnnouncementDaoImpl(Database database) {
        this.database = database;
    }

    @Override
    public void save(Announcement announcement) {
        announcement.setId(IdGenerator.getAnnouncementId());
        database.announcements.add(announcement);
    }
}

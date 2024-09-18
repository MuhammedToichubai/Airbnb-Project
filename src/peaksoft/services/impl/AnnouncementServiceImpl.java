package peaksoft.services.impl;

import peaksoft.dao.impl.AnnouncementDaoImpl;
import peaksoft.dao.impl.UserDaoImpl;
import peaksoft.exceptions.NotFoundException;
import peaksoft.models.Announcement;
import peaksoft.models.User;
import peaksoft.services.AnnouncementService;

public class AnnouncementServiceImpl implements AnnouncementService {
    private final AnnouncementDaoImpl announcementDao;
    private final UserDaoImpl userDao;

    public AnnouncementServiceImpl(AnnouncementDaoImpl announcementDao, UserDaoImpl userDao) {
        this.announcementDao = announcementDao;
        this.userDao = userDao;
    }

    @Override
    public String save(Long userId, Announcement announcement) {
        User foundUser;
        try {
           foundUser = userDao.findById(userId);
        } catch (NotFoundException e) {
           return e.getMessage();
        }
        announcement.setOwner(foundUser);
        announcementDao.save(announcement);

        System.out.println(announcement.getOwner());
        return "Successfully saved!";
    }
}

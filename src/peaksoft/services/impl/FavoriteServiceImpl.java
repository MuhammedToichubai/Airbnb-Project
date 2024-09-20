package peaksoft.services.impl;

import peaksoft.dao.FavoriteDao;
import peaksoft.dao.impl.AnnouncementDaoImpl;
import peaksoft.dao.impl.UserDaoImpl;
import peaksoft.models.Announcement;
import peaksoft.models.Favorite;
import peaksoft.models.IdGenerator;
import peaksoft.models.User;
import peaksoft.services.FavoriteService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FavoriteServiceImpl implements FavoriteService {
    private final FavoriteDao favoriteDao;
    private final UserDaoImpl userDao;
    private final AnnouncementDaoImpl announcementDao;

    public FavoriteServiceImpl(FavoriteDao favoriteDao, UserDaoImpl userDao, AnnouncementDaoImpl announcementDao) {
        this.favoriteDao = favoriteDao;
        this.userDao = userDao;
        this.announcementDao = announcementDao;
    }

    @Override
    public void favoriteAnnouncement(Long userId, Long announcementId) {
        try {
            User foundUser = userDao.findById(userId);
            Announcement foundAnn = announcementDao.findById(announcementId);
            Favorite favorite = foundUser.getFavorite();
            favorite.setUser(foundUser);
            favorite.getAnnouncements().add(foundAnn);
            favoriteDao.save(favorite);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public Map<User, List<Announcement>> getAllByUserId(Long userId) {
        Map<User, List<Announcement>> userAnnouncementMap = new HashMap<>();
        User userById = userDao.findById(userId);
        Favorite favorite = userById.getFavorite();
        userAnnouncementMap.put(favorite.getUser(), favorite.getAnnouncements());
        return userAnnouncementMap;
    }
}

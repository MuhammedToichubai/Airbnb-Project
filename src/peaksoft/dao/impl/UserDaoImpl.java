package peaksoft.dao.impl;

import peaksoft.dao.CustomDao;
import peaksoft.dao.UserDao;
import peaksoft.databse.Database;
import peaksoft.exceptions.NotFoundException;
import peaksoft.models.Favorite;
import peaksoft.models.IdGenerator;
import peaksoft.models.User;

import java.util.List;

public class UserDaoImpl implements UserDao, CustomDao<User, Long> {
    private final Database databas;

    public UserDaoImpl(Database database) {
        this.databas = database;
    }

    @Override
    public void save(User user) {
        user.setId(IdGenerator.getUserId());
        Favorite favorite = new Favorite();
        favorite.setId(IdGenerator.getFavoriteId());
        user.setFavorite(favorite);
        databas.users.add(user);
    }

    @Override
    public User findById(Long id) {
        for (User user : databas.users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        throw new NotFoundException("User with id: " + id + " not found!");
    }

    @Override
    public List<User> findAll() {
        return databas.users;
    }
}

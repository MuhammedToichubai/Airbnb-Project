package peaksoft.dao.impl;

import peaksoft.dao.FavoriteDao;
import peaksoft.databse.Database;
import peaksoft.models.Favorite;

import java.util.List;

public class FavoriteDaoImpl implements FavoriteDao {
    private final Database database;

    public FavoriteDaoImpl(Database database) {
        this.database = database;
    }

    @Override
    public void save(Favorite favorite) {
        database.favorites.add(favorite);
    }

    @Override
    public List<Favorite> getAll() {
        return database.favorites;
    }
}

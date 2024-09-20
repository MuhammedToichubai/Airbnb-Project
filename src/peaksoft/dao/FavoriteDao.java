package peaksoft.dao;

import peaksoft.models.Favorite;

import java.util.List;

public interface FavoriteDao {
    void save(Favorite favorite);

    List<Favorite> getAll();
}

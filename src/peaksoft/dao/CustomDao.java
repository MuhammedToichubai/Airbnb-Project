package peaksoft.dao;


import java.util.List;

public interface CustomDao<T, I> {

    void save(T t);

    T findById(I i);

    List<T> findAll();

}

package ua.nure.odnokozov.admission.committee.dao;

import java.util.List;

public interface EntityDao<T> {

    T getById(long id);

    List<T> getAll();

    T create(T entity);
  
    T update(T entity);

    boolean delete(long id);
}

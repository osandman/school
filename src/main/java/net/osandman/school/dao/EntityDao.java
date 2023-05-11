package net.osandman.school.dao;

public interface EntityDao<T> {

    void add(T... entities);

    T getById(long id);

    void removeById(long id);

    void update(T... entities);

}

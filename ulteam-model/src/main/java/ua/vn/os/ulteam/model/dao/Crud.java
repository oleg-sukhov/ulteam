package ua.vn.os.ulteam.model.dao;

import ua.vn.os.ulteam.model.entity.Entity;

import java.util.List;

/**
 * @Autor os
 */
public interface Crud<T extends Entity> {
    public Long saveOrUpdate(T entity);
    public T get(Long id);
    public void update(T entity);
    public void delete(T entity);
    public void delete(Long id);
    public long getCount();

}

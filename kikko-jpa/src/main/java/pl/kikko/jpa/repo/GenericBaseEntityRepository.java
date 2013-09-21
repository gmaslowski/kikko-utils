package pl.kikko.jpa.repo;

import pl.kikko.jpa.entity.BaseEntity;

import java.io.Serializable;

public interface GenericBaseEntityRepository<T extends BaseEntity> {

    T getById(Serializable entityId);

    void save(T entity);

    void delete(Serializable entityId);

    void delete(T entity);

    boolean exists(Long entityId);
}

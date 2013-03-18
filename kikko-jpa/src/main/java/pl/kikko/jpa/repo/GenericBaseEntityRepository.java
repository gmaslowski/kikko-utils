package pl.kikko.jpa.repo;

import java.io.Serializable;
import pl.kikko.jpa.entity.BaseEntity;

public interface GenericBaseEntityRepository<T extends BaseEntity, ID extends Serializable> {

    T getById(ID entityId);

    void save(T entity);

    void delete(ID entityId);

    void delete(T entity);
}

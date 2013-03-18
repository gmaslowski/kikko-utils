package pl.kikko.jpa.repo;

import java.io.Serializable;
import javax.persistence.EntityManager;
import pl.kikko.jpa.entity.BaseEntity;

public class AbstractBaseEntityJpaRepository<T extends BaseEntity, ID extends Serializable> implements GenericBaseEntityRepository<T, ID> {

    private EntityManager em;

    private Class<T> persistentClass;

    @Override
    public T getById(ID entityId) {
        return em.find(persistentClass, entityId);
    }

    @Override
    public void save(T entity) {
        em.merge(entity);
    }

    @Override
    public void delete(ID entityId) {
        em.remove(em.find(persistentClass, entityId));
    }

    @Override
    public void delete(T entity) {
        em.remove(entity);
    }

}

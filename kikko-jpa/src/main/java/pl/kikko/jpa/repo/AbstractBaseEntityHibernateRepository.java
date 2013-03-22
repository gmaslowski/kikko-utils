package pl.kikko.jpa.repo;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import pl.kikko.jpa.entity.BaseEntity;

public class AbstractBaseEntityHibernateRepository<T extends BaseEntity, ID extends Serializable>
        implements GenericBaseEntityRepository<T, ID> {

    protected SessionFactory sessionFactory;

    private Class<T> persistentClass;

    protected Session session() {
        return sessionFactory.getCurrentSession();
    }

    @SuppressWarnings("unchecked")
    @Override
    public T getById(ID entityId) {
        return (T) session().load(persistentClass, entityId);
    }

    @Override
    public void save(T entity) {
        session().saveOrUpdate(entity);
    }

    @Override
    public void delete(ID entityId) {
        session().delete(session().load(persistentClass, entityId));
    }

    @Override
    public void delete(T entity) {
        session().delete(entity);
    }

}

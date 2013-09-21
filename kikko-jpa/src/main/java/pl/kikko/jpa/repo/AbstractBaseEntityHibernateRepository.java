package pl.kikko.jpa.repo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pl.kikko.jpa.entity.BaseEntity;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

public abstract class AbstractBaseEntityHibernateRepository<T extends BaseEntity> implements GenericBaseEntityRepository<T> {

    protected SessionFactory sessionFactory;
    protected Class<T> persistentClass;

    @SuppressWarnings("unchecked")
    public AbstractBaseEntityHibernateRepository() {
        this.persistentClass = ((Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    protected Session session() {
        return sessionFactory.getCurrentSession();
    }

    @SuppressWarnings("unchecked")
    @Override
    public T getById(Serializable entityId) {
        return (T) session().load(persistentClass, entityId);
    }

    @Override
    public void save(T entity) {
        session().saveOrUpdate(entity);
    }

    @Override
    public void delete(Serializable entityId) {
        session().delete(session().load(persistentClass, entityId));
    }

    @Override
    public void delete(T entity) {
        session().delete(entity);
    }

    @Override
    public boolean exists(Long entityId) {
        return session().get(persistentClass, entityId) != null;
    }

    protected abstract void setSessionFactory(SessionFactory sessionFactory);
}

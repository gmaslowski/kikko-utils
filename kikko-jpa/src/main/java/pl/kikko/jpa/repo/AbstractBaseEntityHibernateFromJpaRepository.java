package pl.kikko.jpa.repo;

import org.hibernate.Session;
import pl.kikko.jpa.entity.BaseEntity;

import javax.persistence.EntityManager;

public abstract class AbstractBaseEntityHibernateFromJpaRepository<T extends BaseEntity> extends AbstractBaseEntityJpaRepository<T> {

    protected Session session() {
        return (Session) em.getDelegate();
    }

    protected abstract void setEntityManager(EntityManager em);
}

package pl.kikko.jpa.repo;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.hibernate.Session;

import pl.kikko.jpa.entity.BaseEntity;

public abstract class AbstractBaseEntityHibernateFromJpaRepository<T extends BaseEntity, ID extends Serializable>
        extends AbstractBaseEntityJpaRepository<T, ID> {

    protected Session session() {
        return (Session) em.getDelegate();
    }

    protected abstract void setEntityManager(EntityManager em);
}

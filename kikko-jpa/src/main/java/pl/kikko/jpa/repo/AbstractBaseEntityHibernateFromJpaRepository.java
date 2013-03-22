package pl.kikko.jpa.repo;

import java.io.Serializable;

import org.hibernate.Session;

import pl.kikko.jpa.entity.BaseEntity;

public class AbstractBaseEntityHibernateFromJpaRepository<T extends BaseEntity, ID extends Serializable>
		extends AbstractBaseEntityJpaRepository<T, ID> {

	protected Session session() {
		return (Session) em.getDelegate();
	}

}

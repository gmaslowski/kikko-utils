package pl.kikko.jpa.repo;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;
import pl.kikko.jpa.entity.BaseEntity;

public abstract class AbstractBaseEntityJpaRepository<T extends BaseEntity, ID extends Serializable>
		implements GenericBaseEntityRepository<T, ID> {

	protected EntityManager em;

	protected Class<T> persistentClass;

	@SuppressWarnings("unchecked")
	public AbstractBaseEntityJpaRepository() {
		this.persistentClass = ((Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0]);
	}

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

	@Override
	public boolean exists(Long entityId) {
		return em.find(persistentClass, entityId) != null;
	}

	protected abstract void setEntityManager(EntityManager em);

}

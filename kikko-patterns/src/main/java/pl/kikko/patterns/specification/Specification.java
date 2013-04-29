package pl.kikko.patterns.specification;

public interface Specification<T> {

	boolean isSatisfiedBy(T o);

	Specification<T> and(Specification<T> spec);

	Specification<T> or(Specification<T> spec);

	Specification<T> not(Specification<T> spec);
	
}

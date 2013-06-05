package pl.kikko.patterns.specification.generic;

public interface Specification<T> {

    boolean isSatisfiedBy(Object o);

    Specification<T> and(Specification<T> spec);

    Specification<T> or(Specification<T> spec);

    Specification<T> not(Specification<T> spec);

}

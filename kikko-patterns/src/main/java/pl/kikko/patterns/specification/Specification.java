package pl.kikko.patterns.specification;

public interface Specification {

    boolean isSatisfiedBy(Object o);

    Specification and(Specification spec);

    Specification or(Specification spec);

    Specification not(Specification spec);
}

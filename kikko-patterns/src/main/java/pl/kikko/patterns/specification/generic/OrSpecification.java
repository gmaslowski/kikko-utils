package pl.kikko.patterns.specification.generic;

public class OrSpecification<T> extends AbstractSpecification<T> {

    private final Specification<T> spec1;
    private final Specification<T> spec2;

    public OrSpecification(Specification<T> spec1, Specification<T> spec2) {
        this.spec1 = spec1;
        this.spec2 = spec2;
    }

    @Override
    public boolean isSatisfiedBy(Object o) {
        return spec1.isSatisfiedBy(o) || spec2.isSatisfiedBy(o);
    }

}

package pl.kikko.patterns.specification.generic;

public class AndSpecification<T> extends AbstractSpecification<T> {

    private Specification<T> spec1;
    private Specification<T> spec2;

    public AndSpecification(Specification<T> spec1, Specification<T> spec2) {
        this.spec1 = spec1;
        this.spec2 = spec2;
    }

    @Override
    public boolean isSatisfiedBy(Object o) {
        return spec1.isSatisfiedBy(o) && spec2.isSatisfiedBy(o);
    }

}

package pl.kikko.patterns.specification.generic;

public class NotSpecification<T> extends AbstractSpecification<T> {

    private Specification<T> specification;

    public NotSpecification(Specification<T> specification) {
        this.specification = specification;
    }

    @Override
    public boolean isSatisfiedBy(Object o) {
        return !specification.isSatisfiedBy(o);
    }

}

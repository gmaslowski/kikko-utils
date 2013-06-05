package pl.kikko.patterns.specification;

public class NotSpecification extends AbstractSpecification {

    private Specification specification;

    public NotSpecification(Specification specification) {
        this.specification = specification;
    }

    @Override
    public boolean isSatisfiedBy(Object o) {
        return !specification.isSatisfiedBy(o);
    }
}

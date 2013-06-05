package pl.kikko.patterns.specification;

public class OrSpecification extends AbstractSpecification {

    private final Specification spec1;
    private final Specification spec2;

    public OrSpecification(Specification spec1, Specification spec2) {
        this.spec1 = spec1;
        this.spec2 = spec2;
    }

    @Override
    public boolean isSatisfiedBy(Object o) {
        return spec1.isSatisfiedBy(o) || spec2.isSatisfiedBy(o);
    }
}

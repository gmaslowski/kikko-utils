package pl.kikko.patterns.specification;

public class AndSpecification extends AbstractSpecification {

    private Specification spec1;
    private Specification spec2;

    public AndSpecification(Specification spec1, Specification spec2) {
        this.spec1 = spec1;
        this.spec2 = spec2;
    }

    @Override
    public boolean isSatisfiedBy(Object o) {
        return spec1.isSatisfiedBy(o) && spec2.isSatisfiedBy(o);
    }
}

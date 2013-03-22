package pl.kikko.patterns.specification;

public abstract class AbstractSpecification implements Specification {

	@Override
	public Specification or(Specification specification) {
		return new OrSpecification(this, specification);
	}

	@Override
	public Specification and(Specification specification) {
		return new AndSpecification(this, specification);
	}

	@Override
	public Specification not(Specification specification) {
		return new NotSpecification(this);
	}
}

package pl.kikko.patterns.specification;

public class NotSpecification<T> extends AbstractSpecification<T> {

	private Specification<T> specification;

	public NotSpecification(Specification<T> specification) {
		this.specification = specification;
	}

	@Override
	public boolean isSatisfiedBy(T o) {
		return !specification.isSatisfiedBy(o);
	}

}

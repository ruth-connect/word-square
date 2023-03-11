package uk.me.ruthmills.wordsquare.predicate;

import java.util.List;
import java.util.function.Predicate;

/**
 * Predicate class to take a list of predicates and execute them in sequence,
 * short-circuiting if any of them return false.
 * 
 * @author ruth
 */
public class ListPredicate<T> implements Predicate<T> {

	// List of predicates to execute.
	private final List<Predicate<T>> predicates;

	/**
	 * Constructor. Takes the list of predicates to execute in sequence.
	 * 
	 * @param predicates The list of predicates.
	 */
	public ListPredicate(final List<Predicate<T>> predicates) {
		this.predicates = predicates;
	}

	/**
	 * Test if the supplied object matches ALL the predicates in the list. If any
	 * predicates return false, we short-circuit and don't bother executing the
	 * rest.
	 * 
	 * @param object The object to test.
	 * @return true if ALL the predicates match, or false if not.
	 */
	@Override
	public boolean test(final T object) {
		// allMatch() is a short-circuiting function.
		return predicates.stream().allMatch(predicate -> predicate.test(object));
	}
}

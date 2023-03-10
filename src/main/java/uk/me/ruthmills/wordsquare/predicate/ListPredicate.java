package uk.me.ruthmills.wordsquare.predicate;

import java.util.List;
import java.util.function.Predicate;

/**
 * Predicate class to take a list of predicates and execute them in sequence,
 * short-circuiting if any of them return false.
 * 
 * @author ruth
 */
public class ListPredicate implements Predicate<String> {

	// List of predicates to execute.
	private final List<Predicate<String>> predicates;

	/**
	 * Constructor. Takes the list of predicates to execute in sequence.
	 * 
	 * @param predicates The list of predicates.
	 */
	public ListPredicate(final List<Predicate<String>> predicates) {
		this.predicates = predicates;
	}

	/**
	 * Test if the supplied word matches ALL the predicates in the list. If any
	 * predicates return false, we short-circuit and don't bother executing the
	 * rest.
	 * 
	 * @param word The word to test.
	 * @return true if ALL the predicates match, or false if not.
	 */
	@Override
	public boolean test(final String word) {
		// allMatch() is a short-circuiting function.
		return predicates.stream().allMatch(predicate -> predicate.test(word));
	}
}

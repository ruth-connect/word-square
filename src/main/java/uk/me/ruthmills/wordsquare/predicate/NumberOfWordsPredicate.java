package uk.me.ruthmills.wordsquare.predicate;

import java.util.function.Predicate;

import uk.me.ruthmills.wordsquare.solution.WordSquare;

/**
 * Predicate to check if a word square contains the correct number of words.
 * 
 * @author ruth
 */
public class NumberOfWordsPredicate implements Predicate<WordSquare> {

	// number of words.
	private final int length;

	/**
	 * Constructor.
	 * 
	 * @param length The expected number of words.
	 */
	public NumberOfWordsPredicate(final int length) {
		this.length = length;
	}

	/**
	 * Check if the word square contains the correct number of words.
	 * 
	 * @param wordSquare The word square to check.
	 * @return true if the word square contains the correct number of words, or
	 *         false if not.
	 */
	@Override
	public boolean test(final WordSquare wordSquare) {
		return wordSquare.getWords().size() == length;
	}
}

package uk.me.ruthmills.wordsquare.predicate;

import java.util.function.Predicate;

/**
 * Predicate class to check if a string is of the required length.
 */
public class WordLengthPredicate implements Predicate<String> {

	/**
	 * Length of string to match.
	 */
	private final int length;

	/**
	 * Constructor. Takes the number of letters in the string we want to match.
	 * 
	 * @param length The required length (in number of letters) of the string.
	 */
	public WordLengthPredicate(final int length) {
		this.length = length;
	}

	/**
	 * Check if the supplied word contains the required number of letters.
	 * 
	 * @param word The word to test.
	 * @return true if the word has the required number of letters, or false if not.
	 */
	@Override
	public boolean test(final String word) {
		return word.length() == length;
	}
}

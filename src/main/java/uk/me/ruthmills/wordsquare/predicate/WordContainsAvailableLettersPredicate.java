package uk.me.ruthmills.wordsquare.predicate;

import java.util.function.Predicate;

import uk.me.ruthmills.wordsquare.letters.AvailableLetters;

/**
 * Predicate class to check if a word contains only the letters available.
 * 
 * @author ruth
 */
public class WordContainsAvailableLettersPredicate implements Predicate<String> {

	// Available letters to match.
	private final AvailableLetters letters;

	/**
	 * Constructor. Takes the available letters (the word MUST contain only those
	 * letters).
	 * 
	 * @param letters The available letters.
	 */
	public WordContainsAvailableLettersPredicate(final AvailableLetters letters) {
		this.letters = letters;
	}

	/**
	 * Check if the supplied word contains ONLY a subset of the available letters.
	 * 
	 * @param word The word to check.
	 * @return true if the word contains ONLY a subset of the available letters.
	 *         false if not.
	 */
	@Override
	public boolean test(final String word) {
		return letters.isWordFormable(word);
	}
}

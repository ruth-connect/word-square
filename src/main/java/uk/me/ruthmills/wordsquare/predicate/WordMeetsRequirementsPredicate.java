package uk.me.ruthmills.wordsquare.predicate;

import java.util.List;
import java.util.function.Predicate;

/**
 * Predicate to check if a given word meets the requirements, based on the
 * existing words.
 * 
 * @author ruth
 */
public class WordMeetsRequirementsPredicate implements Predicate<String> {

	private final List<String> words;

	/**
	 * Constructor.
	 * 
	 * @param words List of words.
	 */
	public WordMeetsRequirementsPredicate(final List<String> words) {
		this.words = words;
	}

	/**
	 * Check if the word meets the requirements for the word square, based on the
	 * existing words in the word square.
	 * 
	 * @param word The word to check.
	 * @return true if the word meets the requirements, or false if not.
	 */
	@Override
	public boolean test(String word) {
		return word.charAt(0) == words.get(0).charAt(words.size());
	}
}

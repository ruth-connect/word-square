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
	public boolean test(final String word) {
		// Doing this check "old school" (using a for loop) is faster than doing it in
		// the "functional" way using an IntStream that we were doing previously.
		// This shaved a further 115 seconds off the time to complete the 7-letter word
		// square (in addition to executing this predicate first, before checking if the
		// word can be formed from the remaining letters) on my 2013-vintage Intel i7
		// laptop running Windows 10.
		// I think it's worth doing this to gain the performance improvement, as it
		// is still readable.
		int numWords = words.size();
		for (int index = 0; index < numWords; index++) {
			if (word.charAt(index) != words.get(index).charAt(numWords)) {
				return false;
			}
		}
		return true;
	}
}

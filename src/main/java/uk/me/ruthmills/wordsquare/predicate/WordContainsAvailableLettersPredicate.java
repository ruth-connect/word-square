package uk.me.ruthmills.wordsquare.predicate;

import java.util.function.Predicate;

import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.bag.HashBag;

/**
 * Predicate class to check if a word contains only the letters available.
 * 
 * @author ruth
 */
public class WordContainsAvailableLettersPredicate implements Predicate<String> {

	// Available letters to match.
	private final String letters;

	/**
	 * Constructor. Takes the available letters (the word MUST contain only those
	 * letters).
	 * 
	 * @param letters The available letters.
	 */
	public WordContainsAvailableLettersPredicate(final String letters) {
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
		// Create a hash bag of available letters.
		Bag<Byte> availableLetters = new HashBag<Byte>();
		for (Byte letter : letters.getBytes()) {
			availableLetters.add(letter);
		}

		// Iterate through each letter in the word.
		for (Byte letter : word.getBytes()) {
			// If the letter is not in the available letters, return false.
			if (!availableLetters.remove(letter, 1)) {
				return false;
			}
		}

		// All the letters in the word are available. Return true.
		return true;
	}
}

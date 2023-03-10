package uk.me.ruthmills.wordsquare.predicate;

import java.util.function.Predicate;

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
		// Call the recursive function to check if the word contains ONLY a subset of
		// the available letters.
		return wordContainsAvailableLetters(word, letters);
	}

	/**
	 * Private recursive function to check if the word contains ONLY a subset of the
	 * available letters.
	 * 
	 * @param word             The word to check.
	 * @param availableLetters The available letters.
	 * @return true if the word contains ONLY a subset of the available letters.
	 *         false if not.
	 */
	private boolean wordContainsAvailableLetters(final String word, final String availableLetters) {
		final String firstLetter = word.substring(0, 1); // get the first letter of the word.

		// Do the available letters contain the first letter of the word?
		if (availableLetters.contains(firstLetter)) {
			// Yes they do.

			// Have we reached the last letter of the word we are checking?
			if (word.length() == 1) {
				// Yes we have. All letters in the word are from the available letters. Return
				// true.
				return true;
			} else {
				// Work out which letters are still available.
				final String remainingLetters = availableLetters.replace(firstLetter, "");

				// Recurse until we reach the last letter of the word.
				return wordContainsAvailableLetters(word.substring(1, word.length()), remainingLetters);
			}
		} else {
			// No they don't contain the first letter of the word. Return false.
			return false;
		}
	}
}

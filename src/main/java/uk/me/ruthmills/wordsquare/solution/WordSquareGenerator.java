package uk.me.ruthmills.wordsquare.solution;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import uk.me.ruthmills.wordsquare.predicate.WordContainsAvailableLettersPredicate;

/**
 * This class generates all possible word squares for the input parameters.
 * These will NOT all be valid. We can filter out the invalid ones later.
 * 
 * @author ruth
 *
 */
public class WordSquareGenerator {

	/**
	 * Get all possible word square combinations (including ones which might not be
	 * valid).
	 * 
	 * @param length  Number of letters in each word.
	 * @param letters Available letters to create the words from.
	 * @throws IOException        Thrown if we cannot read from the dictionary file.
	 * @throws URISyntaxException Thrown if there is a problem with the URI syntax.
	 */
	public static List<WordSquare> getAllPossibleCombinations(final int length, final String letters)
			throws IOException, URISyntaxException {
		final List<String> wordShortlist = WordShortlist.getWordShortlist(length, letters);
		return wordShortlist.stream().flatMap(
				word -> getAllWordSquares(word, length, letters, wordShortlist, new ArrayList<String>()).stream())
				.collect(Collectors.toList());
	}

	/**
	 * Get all word squares (including invalid ones) for a given starting word,
	 * available letters, and word shortlist.
	 * 
	 * @param word          The current word.
	 * @param length        Required length of each word in the word square.
	 * @param letters       The available letters.
	 * @param wordShortlist The word shortlist.
	 * @param words         The list of words so far.
	 */
	static List<WordSquare> getAllWordSquares(final String word, final int length, final String letters,
			final List<String> wordShortlist, final List<String> words) {

		String remainingLetters = WordSquareGenerator.getRemainingLetters(word, letters);
		WordContainsAvailableLettersPredicate wordContainsAvailableLettersPredicate = new WordContainsAvailableLettersPredicate(
				remainingLetters);
		List<String> remainingWords = wordShortlist.stream().filter(wordContainsAvailableLettersPredicate)
				.collect(Collectors.toList());
		return Collections.emptyList();
	}

	/**
	 * Get the remaining letters after taking the letters from the current word away
	 * from the available letters.
	 * 
	 * @param word    The word.
	 * @param letters The available letters.
	 * @return The remaining letters.
	 */
	static String getRemainingLetters(final String word, final String letters) {
		// If we have run out of letters in the word, return the remaining letters left
		// from the available letters.
		if (word.length() == 0) {
			return letters;
		} else {
			// Get the first letter of the word.
			final String firstLetter = word.substring(0, 1);

			// Remove the first letter of the word from the available letters.
			String remainingLetters = letters.replaceFirst(firstLetter, "");

			// Recursively call this function, removing the first letter from the word.
			return getRemainingLetters(word.substring(1, word.length()), remainingLetters);
		}
	}
}

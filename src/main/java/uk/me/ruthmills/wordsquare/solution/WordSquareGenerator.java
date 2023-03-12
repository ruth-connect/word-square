package uk.me.ruthmills.wordsquare.solution;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.ListUtils;

import uk.me.ruthmills.wordsquare.letters.AvailableLetters;
import uk.me.ruthmills.wordsquare.letters.AvailableLettersFactory;
import uk.me.ruthmills.wordsquare.predicate.WordContainsAvailableLettersPredicate;
import uk.me.ruthmills.wordsquare.predicate.WordMeetsRequirementsPredicate;

/**
 * This class generates valid word squares for the input parameters.
 * 
 * @author ruth
 *
 */
public class WordSquareGenerator {

	/**
	 * Get valid word square combinations.
	 * 
	 * @param length         Number of letters in each word.
	 * @param letters        Available letters to create the words from.
	 * @param firstMatchOnly true to stop at the first matching word square, false
	 *                       to carry on until all possible words are exhausted.
	 * @throws IOException Thrown if we cannot read from the dictionary file.
	 */
	public static List<WordSquare> getValidWordSquares(final int length, final String letters,
			final boolean firstMatchOnly) throws IOException {
		// Create the Available Letters object.
		AvailableLetters availableLetters = AvailableLettersFactory.getInstance(letters);

		// Get the word shortlist - this is the subset of words that are the required
		// length, and are made up of a subset of the letters we have available.
		final List<String> wordShortlist = WordShortlist.getWordShortlist(length, availableLetters);

		// Create an empty list to hold the word squares. This gets passed down and
		// populated as we find each word square.
		final List<WordSquare> wordSquares = new ArrayList<WordSquare>();

		// Iterate through each word in the shortlist.
		for (final String word : wordShortlist) {
			// Get any valid word squares beginning with the current word from the
			// shortlist.
			getValidWordSquaresForStartingWord(word, length, availableLetters, wordShortlist, new ArrayList<String>(),
					wordSquares, firstMatchOnly);

			// If we are to return the first match only, and we have a match, return it.
			if (firstMatchOnly && wordSquares.size() > 0) {
				return wordSquares;
			}
		}
		return wordSquares;
	}

	/**
	 * Get valid word squares for a given starting word, available letters, and word
	 * shortlist.
	 * 
	 * @param word           The current word.
	 * @param length         Required length of each word in the word square.
	 * @param letters        The available letters.
	 * @param wordShortlist  The word shortlist.
	 * @param words          The list of words so far.
	 * @param firstMatchOnly true to stop at the first matching word square, false
	 *                       to carry on until all possible words are exhausted.
	 */
	static void getValidWordSquaresForStartingWord(final String word, final int length, final AvailableLetters letters,
			final List<String> wordShortlist, final List<String> words, final List<WordSquare> wordSquares,
			final boolean firstMatchOnly) {
		// Do we have the required number of words in the list of words to make a word
		// square? And, if so, do we want to return all matches - or if we only want one
		// match, is this the first match?
		if (words.size() == length - 1 && (!firstMatchOnly || wordSquares.size() == 0)) {
			// Add the current word to the list of words.
			final List<String> updatedWords = ListUtils.union(words, Collections.singletonList(word));

			// Add a new word square to end of the list of word squares.
			wordSquares.add(new WordSquare(length, updatedWords));
		} else {
			// Get the remaining letters left, after removing those from the current word
			// from the available letters.
			final AvailableLetters remainingLetters = letters.getRemainingLetters(word);

			if (remainingLetters.getCount() >= length) { // only if we have enough letters left to make a word.
				// Create a predicate based on the remaining letters.
				final WordContainsAvailableLettersPredicate wordContainsAvailableLettersPredicate = new WordContainsAvailableLettersPredicate(
						remainingLetters);

				// Get the remaining words available, by filtering only those left where the
				// word shortlist contains the remaining letters.
				final List<String> remainingWordShortlist = wordShortlist.stream()
						.filter(wordContainsAvailableLettersPredicate).collect(Collectors.toList());

				// Are there any remaining words?
				if (remainingWordShortlist.size() > 0) {
					// Add the current word to the list of words.
					final List<String> updatedWords = ListUtils.union(words, Collections.singletonList(word));

					// Create a predicate based on the words being valid at the next position in the
					// word square.
					WordMeetsRequirementsPredicate wordMeetsRequirementsPredicate = new WordMeetsRequirementsPredicate(
							updatedWords);

					for (final String remainingWord : remainingWordShortlist) {
						if (wordMeetsRequirementsPredicate.test(remainingWord)) {
							// Recursively call this function for the word that is valid.
							getValidWordSquaresForStartingWord(remainingWord, length, remainingLetters,
									remainingWordShortlist, updatedWords, wordSquares, firstMatchOnly);

							// If we are to return the first match only, and we have a match, return now.
							if (firstMatchOnly && wordSquares.size() > 0) {
								return;
							}
						}
					}
				}
			}
		}
	}
}

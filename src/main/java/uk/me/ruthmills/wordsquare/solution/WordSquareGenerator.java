package uk.me.ruthmills.wordsquare.solution;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.collections4.bag.HashBag;

import uk.me.ruthmills.wordsquare.predicate.ListPredicate;
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
		// Get the word shortlist - this is the subset of words that are the required
		// length, and are made up of a subset of the letters we have available.
		final List<String> wordShortlist = WordShortlist.getWordShortlist(length, letters);

		// Create an empty list to hold the word squares. This gets passed down and
		// populated as we find each word square.
		final List<WordSquare> wordSquares = new ArrayList<WordSquare>();

		// Iterate through each word in the shortlist.
		for (final String word : wordShortlist) {
			// Get any valid word squares beginning with the current word from the
			// shortlist.
			getValidWordSquaresForStartingWord(word, length, letters, wordShortlist, new ArrayList<String>(),
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
	static void getValidWordSquaresForStartingWord(final String word, final int length, final String letters,
			final List<String> wordShortlist, final List<String> words, final List<WordSquare> wordSquares,
			final boolean firstMatchOnly) {
		// Can the word be formed from the available letters and is it valid with the
		// existing words?
		if (isWordAbleToBeFormed(word, letters, words)) {
			// Get the remaining letters left, after removing those from the current word
			// from the available letters.
			final String remainingLetters = WordSquareGenerator.getRemainingLetters(word, letters);

			// Add the current word to the list of words.
			final List<String> updatedWords = ListUtils.union(words, Collections.singletonList(word));

			// Do we have the required number of words in the list of words to make a word
			// square? And, if so, do we want to return all matches - or if we only want one
			// match, is this the first match?
			if (updatedWords.size() == length && (!firstMatchOnly || wordSquares.size() == 0)) {
				// Add a new word square to end of the list of word squares.
				wordSquares.add(new WordSquare(length, updatedWords));
			} else if (remainingLetters.length() >= length) { // only if we have enough letters left to make a word.
				// Create a predicate based on the remaining letters.
				final WordContainsAvailableLettersPredicate wordContainsAvailableLettersPredicate = new WordContainsAvailableLettersPredicate(
						remainingLetters);

				// Get the remaining words available, by filtering only those left where the
				// word shortlist contains the remaining letters.
				final List<String> remainingWordShortlist = wordShortlist.stream()
						.filter(wordContainsAvailableLettersPredicate).collect(Collectors.toList());

				// Recursively call this function for each remaining word that meets the
				// requirements.
				for (final String remainingWord : remainingWordShortlist) {
					getValidWordSquaresForStartingWord(remainingWord, length, remainingLetters, remainingWordShortlist,
							updatedWords, wordSquares, firstMatchOnly);

					// If we are to return the first match only, and we have a match, return now.
					if (firstMatchOnly && wordSquares.size() > 0) {
						return;
					}
				}
			}
		}
	}

	/**
	 * Check if the word can be formed from the available letters and meets the
	 * requirements.
	 * 
	 * @param word    The word.
	 * @param letters The available letters.
	 * @param words   The existing words in the word square.
	 * @return true if the word can be formed from the available letters, or false
	 *         if not.
	 */
	static boolean isWordAbleToBeFormed(final String word, final String letters, final List<String> words) {
		// We execute the two predicates in the following order:
		// 1. Check if the word meets the requirements in terms of the existing words in
		// the word square.
		// 2. Check if the word contains the available letters.
		// This is because the first predicate is computationally less expensive, so if
		// it does NOT pass, then we short-circuit at that point, avoiding the more
		// time-consuming check (this shaved off around 160 seconds for solving the
		// 7-letter word square on my 2013-vintage Lenovo Intel i7 Windows laptop).
		final ListPredicate<String> listPredicate = new ListPredicate<String>(Arrays
				.asList(new WordMeetsRequirementsPredicate(words), new WordContainsAvailableLettersPredicate(letters)));
		return listPredicate.test(word);
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
		// Create a bag of remaining letters.
		final Bag<Byte> remainingLetters = new HashBag<Byte>();
		for (final Byte letter : letters.getBytes()) {
			remainingLetters.add(letter);
		}

		// Iterate through each letter in the word.
		for (final Byte letter : word.getBytes()) {
			// Remove the letter from the bag.
			remainingLetters.remove(letter, 1);
		}

		// Return the remaining letters.
		return remainingLetters.stream().map(value -> String.valueOf((char) value.byteValue())).sorted()
				.collect(Collectors.joining());
	}
}

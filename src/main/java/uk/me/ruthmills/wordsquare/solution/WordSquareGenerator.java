package uk.me.ruthmills.wordsquare.solution;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import uk.me.ruthmills.wordsquare.predicate.ListPredicate;
import uk.me.ruthmills.wordsquare.predicate.WordContainsAvailableLettersPredicate;
import uk.me.ruthmills.wordsquare.predicate.WordMeetsRequirementsPredicate;

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
	public static Stream<WordSquare> getAllPossibleCombinations(final int length, final String letters)
			throws IOException, URISyntaxException {
		final List<String> wordShortlist = WordShortlist.getWordShortlist(length, letters);
		return wordShortlist.stream().flatMap(word -> getAllWordSquares(word, length, letters, wordShortlist,
				new ArrayList<String>(), new ArrayList<WordSquare>()));
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
	static Stream<WordSquare> getAllWordSquares(final String word, final int length, final String letters,
			final List<String> wordShortlist, final List<String> words, final List<WordSquare> wordSquares) {
		// Can the word be formed from the available letters?
		if (isWordAbleToBeFormed(word, letters, words)) {
			// Get the remaining letters left, after removing those from the current word
			// from the available letters.
			final String remainingLetters = WordSquareGenerator.getRemainingLetters(word, letters);

			// Create a predicate based on the remaining letters. and the existing words.
			final WordContainsAvailableLettersPredicate wordContainsAvailableLettersPredicate = new WordContainsAvailableLettersPredicate(
					remainingLetters);

			// Get the remaining words available, by filtering only those left where the
			// word shortlist contains the remaining letters.
			final List<String> remainingWordShortlist = wordShortlist.stream()
					.filter(wordContainsAvailableLettersPredicate).collect(Collectors.toList());

			// Add the current word to the list of words.
			List<String> updatedWords = Stream.concat(words.stream(), Collections.singletonList(word).stream())
					.collect(Collectors.toList());

			// Do we have the required number of words in the list of words?
			if (updatedWords.size() == length) {
				// Return a new word square at the end of the list of word squares.
				return Stream.concat(wordSquares.stream(),
						Collections.singletonList(new WordSquare(length, updatedWords)).stream());

			} else {
				// Recursively call this function for each remaining word that meets the
				// requirements.
				return remainingWordShortlist.stream()
						.flatMap(remainingWord -> getAllWordSquares(remainingWord, length, remainingLetters,
								remainingWordShortlist.stream().collect(Collectors.toList()), updatedWords,
								wordSquares));

			}
		} else {
			// Word CANNOT be formed from available letters. Return the word squares we have
			// already.
			return wordSquares.stream();
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
		final ListPredicate<String> listPredicate = new ListPredicate<String>(Arrays
				.asList(new WordContainsAvailableLettersPredicate(letters), new WordMeetsRequirementsPredicate(words)));
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
		// If we have run out of letters in the word, return the remaining letters left
		// from the available letters.
		if (word.length() == 0) {
			return letters;
		} else {
			// Get the first letter of the word.
			final String firstLetter = word.substring(0, 1);

			// Remove the first letter of the word from the available letters.
			final String remainingLetters = letters.replaceFirst(firstLetter, "");

			// Recursively call this function, removing the first letter from the word.
			return getRemainingLetters(word.substring(1, word.length()), remainingLetters);
		}
	}
}

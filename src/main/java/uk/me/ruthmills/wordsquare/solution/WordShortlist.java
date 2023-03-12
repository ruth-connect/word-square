package uk.me.ruthmills.wordsquare.solution;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import uk.me.ruthmills.wordsquare.dictionary.Dictionary;
import uk.me.ruthmills.wordsquare.letters.AvailableLetters;
import uk.me.ruthmills.wordsquare.predicate.ListPredicate;
import uk.me.ruthmills.wordsquare.predicate.WordContainsAvailableLettersPredicate;
import uk.me.ruthmills.wordsquare.predicate.WordLengthPredicate;

/**
 * Word Shortlist. This class will return a shortlist of words of the required
 * length, where each word is made up of a subset of the available letters. We
 * can then go on to solve the word square using only these words.
 * 
 * @author ruth
 *
 */
public class WordShortlist {

	/**
	 * Get the word shortlist for solving the word square. This consists ONLY of
	 * words of the required length, where each word is made up ONLY of a subset of
	 * the available letters.
	 * 
	 * @param length  The required length.
	 * @param letters The available letters to solve the word square.
	 * @return The shortlist of words.
	 * @throws IOException Thrown if we cannot read from the dictionary file.
	 */
	public static List<String> getWordShortlist(final int length, final AvailableLetters letters) throws IOException {
		// This predicate will first match on the required number of letters, and then
		// ensure that each word contains ONLY a subset of the available letters.
		final ListPredicate<String> wordPredicate = new ListPredicate<String>(
				Arrays.asList(new WordLengthPredicate(length), new WordContainsAvailableLettersPredicate(letters)));

		// Return the shortlist of words (only those matching the predicate).
		return Dictionary.getWordsMatchingPredicate(wordPredicate);
	}
}

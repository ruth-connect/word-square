package uk.me.ruthmills.wordsquare.solution;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import uk.me.ruthmills.wordsquare.dictionary.Dictionary;
import uk.me.ruthmills.wordsquare.predicate.ListPredicate;
import uk.me.ruthmills.wordsquare.predicate.WordContainsAvailableLettersPredicate;
import uk.me.ruthmills.wordsquare.predicate.WordLengthPredicate;

/**
 * Class to represent the solution of a word square.
 * 
 * @author ruth
 */
public class Solution {

	// dictionary of all available words.
	private final Dictionary dictionary;

	// predicate to match words of the correct length that contain ONLY a subset of
	// the available letters.
	private final ListPredicate wordPredicate;

	// number of letters in each word.
	private final int length;

	// available letters.
	private final String letters;

	/**
	 * Constructor.
	 * 
	 * @param length  The number of letters in each word.
	 * @param letters The available letters.
	 */
	public Solution(final int length, final String letters) {
		this.dictionary = new Dictionary();

		// This predicate will first match on the required number of letters, and then
		// ensure that each word contains ONLY a subset of the available letters.
		this.wordPredicate = new ListPredicate(
				Arrays.asList(new WordLengthPredicate(length), new WordContainsAvailableLettersPredicate(letters)));

		this.length = length;
		this.letters = letters;
	}

	/**
	 * Solve the word square.
	 * 
	 * @return List of possible solutions to the word square (there may be more than
	 *         one for some cases, e.g. dog, oxo, god AND god, oxo, dog).
	 * @throws IOException        Thrown if we cannot read from the dictionary file.
	 * @throws URISyntaxException Thrown if there is a problem with the URI syntax.
	 */
	List<WordCombination> solveWordSquare() throws IOException, URISyntaxException {
		return Collections.emptyList();
	}

	/**
	 * Get the word shortlist.
	 * 
	 * @return The shortlist of words of the correct length, where each word
	 *         contains ONLY a subset of the available letters.
	 * @throws IOException        Thrown if we cannot read from the dictionary file.
	 * @throws URISyntaxException Thrown if there is a problem with the URI syntax.
	 */
	List<String> getWordShortlist() throws IOException, URISyntaxException {
		return dictionary.getWordsMatchingPredicate(wordPredicate);
	}
}

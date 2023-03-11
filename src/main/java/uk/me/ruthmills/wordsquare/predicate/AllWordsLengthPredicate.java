package uk.me.ruthmills.wordsquare.predicate;

import java.util.function.Predicate;

import uk.me.ruthmills.wordsquare.solution.WordSquare;

/**
 * Predicate class to check that all the words in the word square are the
 * required length.
 * 
 * @author ruth
 *
 */
public class AllWordsLengthPredicate implements Predicate<WordSquare> {

	// Word length predicate that we use to test each word in the word square.
	private final WordLengthPredicate wordLengthPredicate;

	/**
	 * Constructor.
	 * 
	 * @param length The required length of each word.
	 */
	public AllWordsLengthPredicate(final int length) {
		this.wordLengthPredicate = new WordLengthPredicate(length);
	}

	/**
	 * Check that all the words in the word square are of the required length.
	 * 
	 * @param wordSquare The word square to check.
	 * @return true if all the words are the required length, or false if not.
	 */
	@Override
	public boolean test(final WordSquare wordSquare) {
		return wordSquare.getWords().stream().allMatch(wordLengthPredicate);
	}
}

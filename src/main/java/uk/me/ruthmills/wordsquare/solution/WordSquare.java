package uk.me.ruthmills.wordsquare.solution;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import uk.me.ruthmills.wordsquare.predicate.AllWordsLengthPredicate;
import uk.me.ruthmills.wordsquare.predicate.ListPredicate;
import uk.me.ruthmills.wordsquare.predicate.NumberOfWordsPredicate;
import uk.me.ruthmills.wordsquare.predicate.ValidWordSquarePredicate;

/**
 * Class representing a word square. This may or may not be a valid word square.
 * 
 * @author ruth
 */
public class WordSquare {

	// length of each word.
	private final int length;

	// list of words.
	private final List<String> words;

	/**
	 * Constructor.
	 * 
	 * @param length Length of each word in the word square.
	 * @param words  List of words.
	 */
	public WordSquare(final int length, final List<String> words) {
		this.length = length;
		this.words = words;
	}

	/**
	 * Get the list of words.
	 * 
	 * @return The list of words.
	 */
	public List<String> getWords() {
		return words;
	}

	/**
	 * Check if this word square is valid.
	 * 
	 * @return true if the word square is valid, or false if not.
	 */
	public boolean isValid() {
		// We perform the following checks, in the following order:
		// 1. The word square contains the expected number of words.
		// 2. Each word in the word square contains the expected number of letters.
		// 3. The word square is a valid word square (all words reading across are the
		// same as all the words reading down).
		// N.B. The list predicate will short-circuit if any predicate in the list
		// fails.
		ListPredicate<WordSquare> listPredicate = new ListPredicate<WordSquare>(
				Arrays.asList(new NumberOfWordsPredicate(length), new AllWordsLengthPredicate(length),
						new ValidWordSquarePredicate()));
		return listPredicate.test(this);
	}

	/**
	 * Convert to string.
	 * 
	 * @return String representation of this word square.
	 */
	public String toString() {
		return words.stream().map(word -> word + "\n").collect(Collectors.joining());
	}
}

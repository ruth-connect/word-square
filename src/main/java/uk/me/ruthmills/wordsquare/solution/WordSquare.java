package uk.me.ruthmills.wordsquare.solution;

import java.util.List;
import java.util.stream.Collectors;

import uk.me.ruthmills.wordsquare.predicate.ValidWordSquarePredicate;

/**
 * Class representing a word square. This may or may not be a valid word square.
 * 
 * @author ruth
 */
public class WordSquare {

	// Valid word square predicate.
	private final static ValidWordSquarePredicate validWordSquarePredicate = new ValidWordSquarePredicate();

	// list of words.
	private final List<String> words;

	/**
	 * Constructor.
	 * 
	 * @param length Length of each word in the word square.
	 * @param words  List of words.
	 */
	public WordSquare(final int length, final List<String> words) {
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
		return validWordSquarePredicate.test(this);
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

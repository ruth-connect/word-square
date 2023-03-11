package uk.me.ruthmills.wordsquare.solution;

import java.util.Arrays;
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
	private final ValidWordSquarePredicate validWordSquarePredicate;

	// list of words.
	private final List<String> words;

	/**
	 * Constructor.
	 * 
	 * @param length Length of each word in the word square.
	 * @param words  Array of words.
	 */
	public WordSquare(int length, String... words) {
		// Call overloaded constructor.
		this(length, Arrays.asList(words));
	}

	/**
	 * Constructor.
	 * 
	 * @param length Length of each word in the word square.
	 * @param words  List of words.
	 */
	public WordSquare(final int length, final List<String> words) {
		this.validWordSquarePredicate = new ValidWordSquarePredicate(length);
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

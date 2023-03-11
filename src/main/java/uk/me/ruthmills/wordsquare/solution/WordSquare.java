package uk.me.ruthmills.wordsquare.solution;

import java.util.List;

/**
 * Class representing a word square. This may or may not be a valid word square.
 * 
 * @author ruth
 */
public class WordSquare {

	// list of words.
	private final List<String> words;

	/**
	 * Constructor.
	 * 
	 * @param words List of words.
	 */
	public WordSquare(List<String> words) {
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
}

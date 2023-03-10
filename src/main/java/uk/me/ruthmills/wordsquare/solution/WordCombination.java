package uk.me.ruthmills.wordsquare.solution;

import java.util.List;

/**
 * Class representing a word combination. This may or may not be a valid word
 * square.
 * 
 * @author ruth
 */
public class WordCombination {

	// list of words.
	private final List<String> words;

	/**
	 * Constructor.
	 * 
	 * @param words List of words.
	 */
	public WordCombination(List<String> words) {
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

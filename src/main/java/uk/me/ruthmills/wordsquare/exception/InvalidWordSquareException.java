package uk.me.ruthmills.wordsquare.exception;

import uk.me.ruthmills.wordsquare.solution.WordSquare;

/**
 * Exception thrown if the completed word square is invalid.
 * 
 * @author ruth
 *
 */
public class InvalidWordSquareException extends Exception {

	// Serial version UID.
	private static final long serialVersionUID = 1L;

	// The invalid word square.
	private final WordSquare wordSquare;

	/**
	 * Constructor.
	 * 
	 * @param wordSquare The invalid word square.
	 */
	public InvalidWordSquareException(final WordSquare wordSquare) {
		this.wordSquare = wordSquare;
	}

	/**
	 * Get the invalid word square.
	 * 
	 * @return The invalid word square.
	 */
	public WordSquare getWordSquare() {
		return wordSquare;
	}
}

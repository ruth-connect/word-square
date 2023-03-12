package uk.me.ruthmills.wordsquare.exception;

import uk.me.ruthmills.wordsquare.solution.WordSquare;

/**
 * Exception thrown if the first word square has been solved, and we want to
 * stop at the first valid solution (instead of continuing to see if there are
 * any others).
 * 
 * @author ruth
 *
 */
public class FirstWordSquareSolvedException extends Exception {

	// Serial version UID.
	private static final long serialVersionUID = 1L;

	// The word square that has been solved.
	private final WordSquare wordSquare;

	/**
	 * Constructor.
	 * 
	 * @param wordSquare The word square that has been solved.
	 */
	public FirstWordSquareSolvedException(final WordSquare wordSquare) {
		this.wordSquare = wordSquare;
	}

	/**
	 * Get the word square that has been solved.
	 * 
	 * @return The word square that has been solved.
	 */
	public WordSquare getWordSquare() {
		return wordSquare;
	}
}

package uk.me.ruthmills.wordsquare.solution;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import uk.me.ruthmills.wordsquare.exception.FirstWordSquareSolvedException;
import uk.me.ruthmills.wordsquare.exception.InvalidWordSquareException;

/**
 * This class generates valid word squares for the input parameters.
 * 
 * @author ruth
 *
 */
public class WordSquareGenerator {

	/**
	 * Get valid word square combinations.
	 * 
	 * @param length         Number of letters in each word.
	 * @param letters        Available letters to create the words from.
	 * @param firstMatchOnly true to stop at the first matching word square, false
	 *                       to carry on until all possible words are exhausted.
	 * @throws InvalidWordSquareException Thrown if a word square we are trying to
	 *                                    add is invalid.
	 * @throws IOException                Thrown if we cannot read from the
	 *                                    dictionary file.
	 */
	public static List<WordSquare> getValidWordSquares(final int length, final String letters,
			final boolean firstMatchOnly) throws InvalidWordSquareException, IOException {
		try {
			// Initalise the solution state.
			final SolutionState solutionState = new SolutionState(length, letters, firstMatchOnly);

			// Iterate through each word in the shortlist.
			for (final String word : solutionState.getWordShortlist()) {
				// Get any valid word squares beginning with the current word from the
				// shortlist.
				solutionState.getValidWordSquaresForStartingWord(word);
			}

			// Return the word squares.
			return solutionState.getWordSquares();
		} catch (FirstWordSquareSolvedException ex) {
			// The firstMatchOnly flag is true, and we have found the first word square.
			return Collections.singletonList(ex.getWordSquare());
		}
	}
}

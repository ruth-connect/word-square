package uk.me.ruthmills.wordsquare;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import uk.me.ruthmills.wordsquare.solution.WordSquare;
import uk.me.ruthmills.wordsquare.solution.WordSquareGenerator;

/**
 * Class to solve a word square.
 * 
 * @author ruth
 */
public class WordSquareSolver {

	/**
	 * Solve the word square.
	 * 
	 * @param length  Number of letters in each word.
	 * @param letters Available letters to create the words from.
	 * @return List of possible solutions to the word square (there may be more than
	 *         one for some cases, e.g. dog, oxo, god AND god, oxo, dog).
	 * @throws IOException        Thrown if we cannot read from the dictionary file.
	 * @throws URISyntaxException Thrown if there is a problem with the URI syntax.
	 *                            when attempting to locate the dictionary file.
	 */
	public static List<WordSquare> solveWordSquare(final int length, final String letters)
			throws IOException, URISyntaxException {
		return solveWordSquare(length, letters, false); // return all possible solutions by default, even if more than
														// one.
	}

	/**
	 * Solve the word square.
	 * 
	 * @param length  Number of letters in each word.
	 * @param letters Available letters to create the words from.
	 * @return List of possible solutions to the word square (there may be more than
	 *         one for some cases, e.g. dog, oxo, god AND god, oxo, dog).
	 * @throws IOException        Thrown if we cannot read from the dictionary file.
	 * @throws URISyntaxException Thrown if there is a problem with the URI syntax.
	 *                            when attempting to locate the dictionary file.
	 */
	public static List<WordSquare> solveWordSquare(final int length, final String letters, final boolean firstMatchOnly)
			throws IOException, URISyntaxException {
		// Get the valid word squares, optionally returning only the first if
		// firstMatchOnly is true.
		return WordSquareGenerator.getValidWordSquares(length, letters, firstMatchOnly);
	}
}
